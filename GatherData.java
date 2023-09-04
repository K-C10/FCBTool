import static java.lang.System.*;
import java.util.*;
import java.io.*;

class GatherData {
  
  private static ArrayList<String> ResLog = new ArrayList<String>();
  
  
  static int tempp = 0;
  public static boolean generateInfo(boolean CleanFiles) throws IOException {

    Scanner file = new Scanner(new File("Countries.dat"));

    String holder = ""; int size = Integer.parseInt(file.nextLine());
    String[] temp = new String[size+1]; String Marker = "";
    FileWriter raw;
    Progress Bar = new Progress(Cutils.BarSize, Cutils.Barfilled, Cutils.BarEmpty);



    out.println("Getting raw Information"); // this block of code will go throught the entire list of countries and gather the data on them, then it stores it into an arraylist ResLog for later writing
    
    for(int i=0; i < size; i++)
    {
      
      holder = file.nextLine();
      if(holder.charAt(0)==':') // here we change the marker to what continent we are in right now so that refine data can pass wht right points to the function that will draw them onto the page
      {
        Marker = holder.substring(1, holder.length());
        i--;
        continue;
      }


      ResLog.add(Marker + ">" + holder + ">" + Arrays.toString(gitInfo(holder)));
      Bar.update(i + 1, size);
    }
    file.close();


    temp = new String[ResLog.size()];
    for(int i = 0; i < ResLog.size(); i++)
      temp[i] = ResLog.get(i);



    // writing the raw data to a file for later processing
    raw = new FileWriter(new File("CountryRawData.dat"));  
    raw.write(size + "\n");
    for(int i = 0; i < temp.length; i++)// writing the finished raw data
    {
      raw.write(temp[i] + '\n'); //ahhhhh i figures out the problem, in refine data is caused by us removing the commas before writing and not after making the raw data
    }
    raw.close();
    if(CleanFiles)
      CleanUp();
    file.close();
    
    return true;
  }////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  

  // this function takes in a country name and reads the countries file and searches for the keys in the string array info and writes to that index the returned info. once it is done it returns an array of unporcessed info
  public static String[] gitInfo(String Country_Name) throws IOException {
     String[] info = new String[Cutils.DataPoints.length];
    
    try{
      String[] file = new String[0];
      int[] dataLoc;

      Scanner filerd = new Scanner(new File(Country_Name + ".html"));
      while(filerd.hasNextLine()){
        //file = Cutils.addArrayindex(file);
        file = Cutils.AppendList(file, filerd.nextLine().split(">"));
      }      
      filerd.close();

      /*for(int fl = 0; fl < file.length; fl++)
      {*/
         for(int i = 0; i < Cutils.DataPoints.length; i++) // i is the entry we are on
         {
         
          // holder is the array that holds the raw data
         dataLoc = Cutils.searcharray(Cutils.DataPoints[i], file);

         if(dataLoc[0] != -1){
           for(int x = dataLoc[0]; x < dataLoc[0] + 10; x++) { // info Grabber here

            info[i] += " ~ " +  swapChar(',','\u2587', file[x]);
           }
          }
        if(dataLoc[0] == -1){
          for(int ij = 0; ij < 10; ij++)  
            info[i] += " ~ ";
          //out.println("ERROR with" + file[fl].split(">") + " was not found, check spelling and or caps!?");
        }
        }
     // }
     //info = Cutils.remArrayindexF(info);
    } catch (FileNotFoundException err) {
      Terminal.ErrorLog.add("Error In GatherData.GitInfo! " + Country_Name + ".html File was not found!");
    }
    return info;
  }

  // this function takes in a WebUrl and an output filename, then it makes a file and writes the webpage to it. if any error happens with it it just returns false and adds it to the error log
  

  // this function is very self explanitory, it takes two char, 1 is the target, and 2 is what to swap it with, then the string comes in to be the programs data / is what the program is edditing
  private static String swapChar(char target, char swaper, String input){
    String output = "";
    for(int i = 0; i < input.length(); i++)
      {
        if(input.charAt(i)==target){
          output += swaper;
          continue;
        }
        output += input.charAt(i);
      }
    return output;
  }

  // this function ensures that all the files needed to get the info is present, if the file is nonexistant then the program downloads the file to its root directory
  public static String verifyFiles() throws IOException
  {
    Progress bar = new Progress(Cutils.BarSize, Cutils.Barfilled, Cutils.BarEmpty);
    out.println("Verifying Files");
    
    Scanner file = new Scanner(new File("Countries.dat"));
    int size = Integer.parseInt(file.nextLine()), count = 0;
    String holder = "";
    while(file.hasNextLine())
    {
      count++;
      holder = file.nextLine().toLowerCase();
      if(holder.charAt(0)==':')
      continue;
      if(new File(holder + ".html").exists()){
        bar.update(count, size + 6);
        continue;
      }
        Cutils.gitWebpage("https://www.cia.gov/the-world-factbook/countries/" + swapChar(' ','-',holder) + "/",holder);
      
      bar.update(count, size + 6);
    }
    file.close();
    return "";
  }

  private static void CleanUp() throws IOException
  {
    Progress bar = new Progress(Cutils.BarSize, Cutils.Barfilled, Cutils.BarEmpty);
    Scanner file = new Scanner(new File("Countries.dat"));
    String temps; int size = Integer.parseInt(file.nextLine()), count=0;
    out.println("\n--------------\nCleaning Files");
    while(file.hasNextLine())
    {
      count++;
      bar.update(count, size);
      temps = file.nextLine();
      File temp = new File(temps.toLowerCase() + ".html");
      temp.delete();
    }
    file.close();
  }
}
