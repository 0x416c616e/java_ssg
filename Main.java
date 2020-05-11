import java.util.*;
import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Paths;
import java.nio.file.Path;
//XML parsing stuff
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;  
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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
               //update total number of pages, because some articles might have more than one
               File totalFile = new File("data/total_number_of_pages.txt");
               Scanner totalScanner = new Scanner(totalFile);
               int total = Integer.parseInt(totalScanner.nextLine());
               System.out.println("Total: " + total);
               total += numberOfPages;
               totalScanner.close();
               File totalFileWriter = new File("data/total_number_of_pages.txt"); 
               PrintWriter totalWriter = new PrintWriter(totalFileWriter);
               totalWriter.print(total);
               totalWriter.close();
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
                  System.out.println("successfully made new article");
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
               Path destinationPageList = Paths.get("output/blog_temp.html");
               try {               
                  Files.copy(sourcePageListTemplate, destinationPageList, REPLACE_EXISTING);
                  //2.5 get total number of pages from total_number_of_pages.txt
                  File totalFile = new File("data/total_number_of_pages.txt");
                  Scanner totalScanner = new Scanner(totalFile);
                  int total = Integer.parseInt(totalScanner.nextLine());
                  System.out.println("Total: " + total);
                  //3. copy as many blog_post_template.html files as needed (i.e. total == 2, copy twice)
                  //    example filenames: blog_temp.html and temp_article_page_1.html, temp_article_page_2.html
                  
                  Path sourceBlogPostPage = Paths.get("templates/blog_post_template.html");
                  for (int i = 1; i <= total; i++) {
                     String destinationString1 = "output/temp_article_page_" + i + ".html";
                     Path destinationBlogPostPage = Paths.get(destinationString1);
                     Files.copy(sourceBlogPostPage, destinationBlogPostPage, REPLACE_EXISTING);
                  }
                  //3.5 testing XML parsing so I know what to do later
                  File xmlTest = new File("data/article_1_page_1.xml");
                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                  try {
                     DocumentBuilder builder = factory.newDocumentBuilder();
                     try {
                        Document document = builder.parse(xmlTest);
                        NodeList nodeList = document.getDocumentElement().getChildNodes();
                        Node node = nodeList.item(0);
                        String testDate = node.getAttributes().getNamedItem("date").getNodeValue();
                        System.out.println(testDate);
                     } catch (SAXException e) {
                        e.printStackTrace();
                     }
                  } catch (ParserConfigurationException e) {
                     e.printStackTrace();
                  }
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  //4. open data files
                  
                  /*int currentlyCompletedPages = 0;
                    int currentlyCompletedArticles = 0;
                  while (currentlyCompletedPages < total) {
                     //a.) get number_of_pages from article (currentlyCompletedArticles + 1)
                     //b.) while (int i = 1; i <= number_of_pages, i++) {
                     //        take article_[currentlyCompletedArticles] XML page [i]
                     //        and put into temp_article_page_[currentlyCompletedPages + 1]
                               then rename it to the proper name from the url
                     //        currentlyCompletedPages++
                          }
                          currentlyCompletedArticles++
                  }*/
                  
                  //5. find and replace titles in the blog_temp.html file (list of titles with links)
                  //6. find and replace all blog values in each article_X.html file
                  //6.5 for articles which have multiple pages, they need to link to one another
                  //6.6 TO-DO: bootstrap pagination thing at the bottom
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