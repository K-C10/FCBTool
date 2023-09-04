import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class Terminal {
  public static ArrayList<String> ErrorLog = new ArrayList<String>();
  public static String CurrentFont = "Times New Roman";
  public static String ProgramName =  new java.io.File(Terminal.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
  public static boolean cleanHtmlFiles = false;
  public static void main(String args[]) throws IOException {
    Scanner user = new Scanner(System.in);
    int temp = 7;
    boolean running = true;
    //out.println("[DEBUG > " + args.length + "]");

    while (running) {
      if(args.length == 0){
        out.println("----------------------------------------");
        out.println(cleanHtmlFiles ? "Clean Html Files is on" : "Clean Html Files is off");
        out.println("Current Font is " + CurrentFont);
        out.println("1 - Change Font");
        out.println("2 - Toggle HTML File cleaning");
        out.println("3 - Build CountryPages");
        out.println("4 - Clean Files");
        out.println("5 - End Program");
        out.print("> ");
        temp = Integer.parseInt(user.nextLine());
      } else if(args.length == 1) {
          out.println("Headless Mode. args" + Arrays.toString(args));
          temp = Integer.parseInt(args[0]);
          running = false;  
      } else if(args.length == 2) {
        out.println("Headless Mode. args" + Arrays.toString(args));
        temp = Integer.parseInt(args[0]);
        running = false;  
        CurrentFont = args[1];
    }
      Scanner uin = new Scanner(System.in);
      switch (temp) {
        case 1:
          out.print("Enter the Name of the New Font; ");
          CurrentFont = uin.nextLine();
        break;

        case 2:
          cleanHtmlFiles = !cleanHtmlFiles;
        break;

        case 3: // AutoRun
          Cutils.checkFileIntegrity();

          Cutils.loadConfig();

          GatherData.verifyFiles();

          /* if (new File("Errors.txt").exists()) {
            new File("00Refined Data.dat").delete();
            SpecialBuilder.extractor();
            Cutils.inject_Data();
          } */

          new File("00Refined Data.dat").delete();
          new File("CountryRawData.dat").delete(); 

          GatherData.generateInfo(cleanHtmlFiles);

          RefineData.refine();

          RefineData.CompileData();

          writeData(CurrentFont); // Coles Better Font
        break;

        case 4: // clean files
          Cutils.checkFileIntegrity();
          Cutils.loadConfig();
          Cutils.CleanFiles();
        break;

        case 5: // exit program
          running = false;
          uin.close();
        break;
      }

      //
    }
    
    user.close();
  }

  public static void writeData(String font) throws IOException {
    out.println("writing pages");
    int max = 0, count = 0;
    Progress Bar = new Progress(Cutils.BarSize, Cutils.Barfilled, Cutils.BarEmpty);
    Scanner file = new Scanner(new File("00Compiled Data.dat"));
    while(file.hasNextLine()) {
      max++;
      file.nextLine();
    }
    file = new Scanner(new File("00Compiled Data.dat"));

    String holder;

    while (file.hasNextLine()) {
      holder = file.nextLine();
      // out.println(holder);
      Cutils.writeInfoToPage(holder, font);
      Bar.update(++count, max);
    }

    file.close();
  }
}
