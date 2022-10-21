package test.sskim;

import test.sskim.di.MyContainerSvc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AccountService accountSvc = MyContainerSvc.getObject(AccountService.class);
        accountSvc.join("ok");
    }
}
