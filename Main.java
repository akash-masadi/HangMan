import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner sc;
    public static void main(String[] args) throws IOException{
        try{
            sc=new Scanner(System.in);
            FileReader fr=new FileReader("data.txt");
            String keyword ="";
            String meaning="";  
            String sample="";
            boolean readKeyword=false;  
            boolean readMeaning=false;  
            boolean readSample=false;  
            while(fr.ready())
            {
                char c=(char)fr.read();
                if(c=='\n')
                {
                    keyword="";
                    continue;
                }
                else if(!readKeyword)
                {
                    if(c==' ')
                    {
                        readKeyword=true;
                        continue;
                    }
                    keyword+=c;
                }
                else if(!readMeaning)
                {
                    if(c=='"')
                    {
                        readMeaning=true;
                        continue;
                    } 
                        
                    meaning+=c;
                }
                else if(!readSample)
                {
                    if(c=='"') 
                    {
                        int length=keyword.length();
                        solve(keyword.toLowerCase(),length);
                        System.out.println("The Actual Word is --->"+keyword);
                        System.out.println("\n\n");
                        System.out.println("The Meaning is --->"+meaning);
                        System.out.println("\n\n");
                        System.out.println("The Sample Sentence is --->"+sample);
                        System.out.println("\n\n");
                        readSample=true;
                        try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
                        System.out.println("Do you want to try more! \n 1.YES \n 2.NO");
                        if(sc.nextInt()==2)
                        {
                            System.out.println("\n\t\t\t\tThank you!\nBye\n\n");
                            break;
                        }
                        continue;
                    }
                    sample+=c;
                }
                else
                {
                    readKeyword=false;
                    readMeaning=false;
                    readSample=false;
                    keyword=""+c;
                    sample="";
                    meaning="";
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void solve(String str,int len)
    {
        // preprocesss
        HashSet<Character> hasChar = new HashSet<Character>();
        for(int i=0;i<len;i++)
        {
            hasChar.add(str.charAt(i));
        }
        HashSet<Character> revealed = new HashSet<>();
        int count=0;
        while(count<10)
        {
            printStr(str, len, revealed);
            System.out.println("\nChoose the next Character :");
            char c=sc.next().charAt(0);
            if(hasChar.contains(c)) // right choice
            {
                revealed.add(c);
                if(revealed.size()==hasChar.size())
                {
                    System.out.println("Hurray! You did it\n");
                    break;
                }
            }
            else{
                count++;
                System.out.println("OOps! Wrong Choice! \n Lives remianing : "+(10-count));
            }
        }
    }
    public static void printStr(String keyword,int len,HashSet<Character> revealed)
    {
        for(int i=0;i<len;i++)
        {
            char c=keyword.charAt(i);
            if(revealed.contains(c))
            {
                System.out.print(c);
            }
            else{
                System.out.print("_");
            }
            System.out.print(" ");
        }
    } 
}