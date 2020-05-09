import java.util.*;
import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Paths;
import java.nio.file.Path;

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
            System.out.print("Enter number of pages: ");
            int numberOfPages = Integer.parseInt(in.nextLine());
            System.out.print("Enter date (i.e. 02/03/2020): ");
            String newArticleDate = in.nextLine();               
            try {
               File countFile = new File("data/count.txt");
               Scanner countScanner = new Scanner(countFile);
               int count = Integer.parseInt(countScanner.nextLine());
               System.out.println("Count: " + count);
               count += 1;
               countScanner.close();
               File countFileWriter = new File("data/count.txt"); 
               PrintWriter countWriter = new PrintWriter(countFileWriter);
               countWriter.print(count);
               countWriter.close();
               for (int i = 1; i <= numberOfPages; i++) {
                  //getting user input and then writing it to XML files
                  File xmlFile = new File ("data/article_" + count + "_page_" + i + ".xml");
                  PrintWriter out = new PrintWriter(xmlFile);
                  System.out.println("Page " + i);
                  out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                  out.println("<article>");
                  out.println("<number_of_pages>" + numberOfPages + "</number_of_pages>");
                  out.println("<date>" + newArticleDate + "</date>");
                  System.out.print("Enter title: ");
                  String newArticleTitle = in.nextLine();
                  out.println("<title_" + i + ">" + newArticleTitle + "</title_" + i + ">");
                  System.out.print("Enter url (i.e. hello.html or hello_2.html): ");
                  String newArticleUrl = in.nextLine();
                  out.println("<url_" + i + ">" + newArticleUrl + "</url_" + i + ">");
                  System.out.print("Enter img (i.e. chicago.jpg): ");
                  String newArticleImg = in.nextLine();
                  out.println("<img_" + i + ">" + newArticleImg + "</img_" + i + ">");
                  System.out.print("Enter og_img (i.e. opengraph_logo.png): ");
                  String newArticleOgImg = in.nextLine();
                  out.println("<og_img_" + i + ">" + newArticleOgImg + "</og_img_" + i + ">");
                  System.out.print("Enter description for OG meta tag: ");
                  String newArticleDescription = in.nextLine();
                  out.println("<description_" + i + ">" + newArticleDescription + "</description_" + i + ">");
                  System.out.println("Enter body (use <p></p> tags for all text, no linebreaks): ");
                  String newArticleBody = in.nextLine();
                  newArticleBody = newArticleBody.replaceAll("<", "&lt;");
                  newArticleBody = newArticleBody.replaceAll(">", "&gt;");
                  out.println("<body_" + i + ">" + newArticleBody + "</body_" + i + ">");
                  out.println("</article>");
                  out.close();
               }
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            }               
            break;
         case "2":
            //1. read count.txt
            try {
               File countFile = new File("data/count.txt");
               Scanner countScanner = new Scanner(countFile);
               int count = Integer.parseInt(countScanner.nextLine());
               System.out.println("Count: " + count);
               //2. copy blog_page_list_template.html to output folder
               Path sourcePageListTemplate = Paths.get("templates/blog_page_list_template.html");
               Path destinationPageList = Paths.get("output/blog.html");
               try {               
                  Files.copy(sourcePageListTemplate, destinationPageList, REPLACE_EXISTING);
                  //3. copy as many blog_post_template.html files as needed (i.e. count == 2, copy twice)
                  //    example filenames: blog_temp.html and article_1_temp.html, article_2_temp.html, etc.
                  //4. open data files
                  //5. find and replace titles in the blog_temp.html file (list of titles with links)
                  //6. find and replace all blog values in each article_X.html file
                  //7. rename files appropriately, i.e. if article_1.xml has <url> of hello, then rename
                  //   article_1_temp.html to hello.html
                  //8. move files to the github.io folder
               } catch (IOException e) {
                  e.printStackTrace();
               }
               
               
            
            } catch (FileNotFoundException e) {
               e.printStackTrace();
            }
            
            
            break;
         default:
            break;
      }
   }
}