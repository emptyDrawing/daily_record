package test.sskim.junit;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static long THRESHOLD;

    public FindSlowTestExtension(long l) {
        THRESHOLD = l;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String testMethodName = context.getRequiredTestMethod().getName();
        SlowTestTag slowTag = context.getRequiredTestMethod().getAnnotation(SlowTestTag.class);
        
        ExtensionContext.Store store = getStore(context);
    
        Long startTime = store.remove("START_TIME", long.class);
        long duration = System.currentTimeMillis() - startTime;
        if( duration > THRESHOLD && slowTag == null ) {
            System.out.printf("Please consider mark method [%s] with @slowTest .\n",testMethodName);
        }
    
    }
    
    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
