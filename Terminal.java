import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class Terminal {
  public static ArrayList<String> ErrorLog = new ArrayList<String>();
  public static void main(String args[]) throws IOException {
    String font = "Hello Ketta";
    Scanner user = new Scanner(System.in);
    int temp = 7;
    boolean running = true;
    out.println("FCB Tool, \"I Don't care how you get them done, I just want them done!\" -Coach Brady 2022 [Terminal Edition]");
    //out.println("[DEBUG > " + args.length + "]");

    while (running) {
      if(args.length == 0){
        out.print("\n0 = Gather Required Files\n1 = generate Raw Data\n2 = build refined data\n3 = AutoRun\n4 = Clean Files\n5 = stop\n> ");
        temp = Integer.parseInt(user.nextLine());
      } else if(args.length >= 1) {
          out.println("Headless Mode. args" + Arrays.toString(args));
          temp = Integer.parseInt(args[0]);
          running = false;  
          font = args[1];
      }

      switch (temp) {
        case 0: // gather required files
          Cutils.checkFileIntegrity();
          GatherData.verifyFiles();
        break;

        case 1:
        // build rawConutryData
        Cutils.checkFileIntegrity();

          Cutils.loadConfig();

          GatherData.verifyFiles();
         GatherData.generateInfo(Cutils.EraseUnneededfiles);
         break;

        case 2: // build refined data
          Cutils.checkFileIntegrity();
          Cutils.loadConfig();
          // out.println("Verifying files");
          GatherData.verifyFiles();
          out.println("Getting Info");
          GatherData.generateInfo(Cutils.EraseUnneededfiles);
          RefineData.refine();
          out.println("Refined Data Built!");
          RefineData.CompileData();
          break;

        /*case 3: // compile errors // preserved for reminising onto
          out.println("Extracting raw Data");
          // code here to extract raw data
          if (!(new File("00Refined Data.dat").exists())) {
            GatherData.verifyFiles();
            Cutils.loadConfig();
            GatherData.generateInfo(Cutils.EraseUnneededfiles);
            RefineData.refine();

          }
          SpecialBuilder.compileErrors();
          SpecialBuilder.extractor();
          break;*/

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
          new File("CountryRawData.dat").delete(); // issue with data not being right first time being ran but being found the second time might be that the incorrect data is stored somewhere in memory and is refering to that rather than the repaired data

          GatherData.generateInfo(Cutils.EraseUnneededfiles);

          RefineData.refine();

          RefineData.CompileData();

          Terminal.writeData(font); // Coles Better Font
          break;

        case 4: // clean files
          Cutils.checkFileIntegrity();
          Cutils.loadConfig();
          Cutils.CleanFiles();
          break;

        /*case 6:
          Cutils.inject_Data();
        break;*/

        case 5: // exit program
          running = false;
          break;
      }

      //
    }

    user.close();

    // inject data, build refined data, compile errors, autorun,write error logs,
    // clean files
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