package test.sskim;

@MyAnnotation(name = "123", number = 123 )
public class Book {
    
    private static String author = "sskim";
    private static final String isbn = "ISBN:1234";

    private String title;
    public String pageNum = "1234";
    protected String category = "Java";

    public Book(){

    }

    
    public Book(String title, String pageNum, String category) {
        this.title = title;
        this.pageNum = pageNum;
        this.category = category;
    }

    private void privateF() {
        System.out.println("private func");
    }
    
    public void publicF() {
        System.out.println("public func");
    }

    public Integer returnNum() {
        return 100;
    }


}
