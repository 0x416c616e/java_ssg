import java.util.*;

public class Main {
   public static void main(String[] args) {
      System.out.println("What would you like to do?");
      System.out.println("1. Write a new article in XML");
      System.out.println("2. Generate static pages by combining data with templates");
      System.out.print("Choice: ");
      Scanner in = new Scanner(System.in);
      String mainMenuChoice = in.nextLine();
      switch (mainMenuChoice) {
         case "1":
            System.out.print("Enter title: ");
            String newArticleTitle = in.nextLine();
            System.out.print("Enter url (i.e. hello.html): ");
            String newArticleUrl = in.nextLine();
            System.out.print("Enter img (i.e. chicago.jpg): ");
            String newArticleImg = in.nextLine();
            System.out.print("Enter og_img (i.e. opengraph_logo.png): ");
            String newArticleOgImg = in.nextLine();
            System.out.print("Enter date (i.e. 02/03/2020): ");
            String newArticleDate = in.nextLine();
            System.out.print("Enter description for OG meta tag: ");
            String newArticleDescription = in.nextLine();
            System.out.println("Enter body (use <p></p> tags for all text, no linebreaks): ");
            String newArticleBody = in.nextLine();
            //convert the strings into a single string
            //that will be eventually written to the XML file
            //read count.txt to see how many XML articles there are
            //then add one to it and then save count.txt with the new value
            //then make a new one with the appropriate number
            //i.e. if old count.txt == 2, then overwrite 3 to count.txt, then make article_3.xml
            break;
         case "2":
            //1. read count.txt
            //2. copy blog_page_list_template.html to output folder
            //3. copy as many blog_post_template.html files as needed (i.e. count == 2, copy twice)
            //    example filenames: blog_temp.html and article_1_temp.html, article_2_temp.html, etc.
            //4. open data files
            //5. find and replace titles in the blog_temp.html file (list of titles with links)
            //6. find and replace all blog values in each article_X.html file
            //7. rename files appropriately, i.e. if article_1.xml has <url> of hello, then rename
            //   article_1_temp.html to hello.html
            //8. move files to the github.io folder
            break;
         default:
            break;
      }
   }
}