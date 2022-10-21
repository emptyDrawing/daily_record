package test.sskim;

@MyAnnotation(name = "123", number = 123 )
public class Book {
    
    private static String author = "sskim";

    public String title ="nonamed";

    public int nowPage = 0;

    public Book(){

    }

    /**
     * @param title
     * @param nowPage
     */
    public Book(String title) {
        this.title = title;
    }


    public void printInfo() {
        System.out.printf("%s %s \n", author, title);
    }
    
    public int getMaxPage() {
        return 3000;
    }

    public int writePage(int page){
        return nowPage += page;
    }

}
