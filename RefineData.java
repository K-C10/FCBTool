import static java.lang.System.*;
import java.util.*;
import java.io.*;
//import java.math.*;

public class RefineData
{
    public static ArrayList<String> RefinedData = new ArrayList<String>();

    public static void refine() throws IOException {
        out.println("Refining raw data");

        Scanner file = new Scanner(new File("CountryRawData.dat")); // defining needed variables
        int size = Integer.parseInt(file.nextLine()); // getting the amount of contries to refine

        String[] tempData2; String holder, construct, data = "", temp = "";
        //Progress bar = new Progress(Cutils.BarSize, Cutils.Barfilled, Cutils.BarEmpty); // making progress bar

        for(int i = 0; i < size; i++) // for each country, read in the data, split it on the ',' then for each index in that split that data by '~' and get the data from the data offsets Cutils array
        {
            //out.println("Loop ran > " + i);
            ///bar.update(i + 1, size); // updating the progress bar
            

            holder = file.nextLine(); // reading in the data

            //out.println(holder);
            
            construct = holder.split(">")[0] + "/" + holder.split(">")[1] + ">" + holder.split(">")[1] + "~"; // setting up the header for the country 

            //out.println("-------------------------------------------------------------------------" + Arrays.toString(holder.split(">")[2].split(",")) + "---------------------------------------------------------------------------------");

            tempData2 = holder.split(","); // setting up the rest of the data to be used by the main program for refining

            //out.println(Arrays.toString(tempData2) + "\n\n\n\n\n\n\n\n\n\n");

            for(int x = 0; x < tempData2.length; x++)
            {
                //out.println(tempData2[x] + " here was where the loop prints out what its working on");
                //out.println(Cutils.DataPoints[x]);
                try{

                    //out.println(Arrays.toString(tempData2[x].split("~")));
                    
                    data = tempData2[x].split("~")[Cutils.DataOffsets[x]].split("<")[0]; // splitting on the "<" just removes the junk that we dont need, does not affect the main data
                    data = data.replaceAll("[&#]", "");
                    temp = data;
                    //out.println(tempData2[x].split("~")[Cutils.DataOffsets[x]] + " x-> " + x + " dataoffsets -> " + Cutils.DataOffsets[x] + "\n " + Arrays.toString(tempData2[x].split("~")));
                    //out.println(data);
                    //out.println(tempData2[x]);
                    //Thread.sleep(1000);
                    switch (Cutils.DataPoints[x]) {
                        case "Location":
                        //out.println("Location");
                        data = data.split("\u2587")[0];
                        //out.println("Modified Locaiton > " + data);
                        break;

                        case "Geographic coordinates":
                        
                        break;

                        case "Area</a":
                            data = data.split("\\(")[0];
                        //out.println("Area</a");
                        break;

                        case "border countries (":
                        String[] datatemp = data.split(";");
                        data = "";
                        for(String item : datatemp) {
                            data += item.split(" ")[1] + ",";
                        }   
                        data = data.substring(0, data.length() - 1);
                        //out.println("border countries (");
                        /*out.println("\n\n\n" + holder.split(">")[1] + "\"" + data + "\"\n\n\n");
                        if(data.substring(0,5).equals(" DATA")){
                            data = "No border countries";
                        } else {
                            // code here to format the present border countries

                        }*/
                        break;

                        case "Climate":
                        // out.println("Climate");
                        data = data.split("[\u2587;]")[0];
                        break;
                        
                        case "terrain":
                        //out.println("terrain");
                        data = data.split("[\u2587;]")[0];
                        break;

                        case "lowest point":
                        data = data.split(";")[0];
                        // out.println("lowest point");
                        break;

                        case "highest point":
                        data = data.split(";")[0];
                        // out.println("highest point");
                        break;

                        case "Natural resources":
                        // out.println("Natural resources");
                        break;

                        case "Nationality":
                        // out.println("Nationality");
                        break;

                        case "languages":
                        // out.println("languages");
                        data = data.split(" ")[1];
                        break;

                        case "Religions":
                        // out.println("Religions");
                        data = data.split(" ")[1];
                        break;

                        case "Population</a":
                        // out.println("Population</a");
                        break;

                        case "Population growth rate":
                        // out.println("Population growth rate");
                        break;

                        case "Birth rate":
                        // out.println("Birth rate");
                        data = data.split("\\(")[0];
                        break;

                        case "Death rate":
                        // out.println("Death rate");
                        data = data.split("\\(")[0];
                        break;

                        case "Net migration rate":
                        data = "";
                        break;

                        case "Life expectancy at birth":
                        // out.println("Life expectancy at birth");
                        break;

                        case "Country Abbreviation":
                        // todo :
                        // add a new thing in config that is country abbreviations, and where there is a marker in the countries.dat file, there will be a "NA"
                        data = Cutils.CountryAbbreviation[i];
                        break;

                        case "National symbol(s)":
                            //out.println("-------------------------");
                            data = data.split(";")[0];
                        break;

                        case "Government type":
                        // out.println("Government type");
                            data = data.split(" ")[0] + data.split(" ")[1];
                        break;

                        case "Capital":
                            data = data.split("[<;]")[0];
                            //out.println("------------------------------------------------");
                        break;

                        case "Legal system":
                            if(data.contains("apply"))
                                data = data.split("apply")[0] + "apply";    
                            else if(data.contains("system"))
                                data = data.split("system")[0] + "System";
                            else if(data.contains("law"))
                                data = data.split("law")[0] + "law";
                             //    out.println(" Legal System Check");
                        break;

                        case "Industries":
                            data = "";    
                            for(int ii = 0; ii < 3; ii++){
                                data += temp.split("\u2587")[ii].split(";")[0];
                                if(!(ii == 2)) {
                                    data += ",";
                                }
                            }
                        break;

                        case "Exports - commodities":
                            data = "";    
                            for(int ii = 0; ii < 3; ii++){
                                data += temp.split("\u2587")[ii];
                                if(!(ii == 2)) {
                                    data += ",";
                                }
                            }
                        break;

                        case "Imports - commodities":
                            data = "";    
                            for(int ii = 0; ii < 3; ii++){
                                data += temp.split("\u2587")[ii];
                                if(!(ii == 2)) {
                                    data += ",";
                                }
                            }
                        break;

                        case "agricultural land:":
                            data = data.split(" ")[3] + " of land has AG use";
                        break;

                        case "forest:":
                            data = data.split(" ")[1] + " of land is forrested";
                        break;

                        case "Irrigated land":
                            data = data.split(" ")[1] + " sq km of land is irrigated";
                        break;
                        
                        default:
                        //if(!(Terminal.ErrorLog.contains("Warning: The Term \"" + Cutils.DataPoints[x] + "\" was not found in switch statement")))
                            //Terminal.ErrorLog.add("Warning: The Term \"" + Cutils.DataPoints[x] + "\" was not found in switch statement");
                        break;    
                    }

                    //out.println("Data -> " + data + "\n\n");

                    construct += data + "~";  
                    
                    //out.println("Loop Finished Without errors");

                 } catch (ArrayIndexOutOfBoundsException oob){
                    //if(x==3)
                    //{construct += "No Border Countries~";
                    // continue;}

                     //out.println("-----------------------\n\nerror with loop\n\n-----------------------");
                    
                    //out.println(data);

                    Terminal.ErrorLog.add("Error in RefineData.refine()! Raw Data might not have the right length on country " + construct.split(">")[0] + "[" + x + "]or git info did not find the wanted data in config.txt");
                    if(data.split(" ").equals("DATA")) {
                        construct += " ~"; // Gather Data error
                    } else {
                        construct += " ~"; // refined data error
                    }//out.println(Arrays.toString(tempData2));
                }
                  
            }
            RefinedData.add(construct);
        }

        FileWriter outw = new FileWriter(new File("00Refined Data.dat"));
        //out.println(RefinedData);
        for(int i = 0; i < RefinedData.size(); i++)
            outw.write(RefinedData.get(i) + '\n');

        outw.close();
        file.close();
    }

    public static void CompileData() throws FileNotFoundException, IOException{
        // take in the refined data file and add paint coords
        // continent ! "TextData"~Xcoord`Ycoord~1|0 for bigger text, rep last thing seperated by !
        String[] filedata = new String[0];
        String holder, construct, output = "", coord;
        String[] temp;
              
        Scanner file = new Scanner(new File("00Refined Data.dat"));
        int count = 0;

        while(file.hasNextLine())
        {
            filedata = Cutils.addArrayindex(filedata);
            filedata[count] = file.nextLine();
            count++;
        }
        file.close();
        //out.println(Arrays.toString(filedata));
        // 0 North America
        // 1 South America
        // 2 Europe
        // 3 Asia
        // 4 Africa
        // 5 Austrialia

        String continent, country; String[] data;

        for(int i = 0; i < filedata.length; i++)
        {
            holder = filedata[i];
            continent = holder.split("/")[0];
            
            //out.println(">" + continent + "<");
            country   = holder.split("[/>]")[1];
            
            construct = continent + "\u2588" + country + "\u2588";
            //out.println(Arrays.toString(holder.split(">")[1].split("~")) + " <- length = " + holder.split(">")[1].split("~").length);
            data = holder.split(">")[1].split("~");
           
            for(int j = 0; j < data.length; j++)
            {
                try{
                    coord = Cutils.continentDataLocations[getContinet(continent)].split(",")[j];

                    if(coord.split("`")[1].equals("0") && coord.split("`")[0].equals("0"))
                        coord = "960`1330"; // this is the debug to write the undefined 0,0 coords to a readable place

                    // here we will split the coords, turn them into ints, then add a random number to it + or -, then put it back to a string

                    coord = (Integer.parseInt(coord.split("`")[0]) + (int) Math.floor(Math.random() * (5 - -5 + 1) + -5)) + "`" + (Integer.parseInt(coord.split("`")[1]) + (int) Math.floor(Math.random() * (5 - -5 + 1) + -5));

                    if(j == 0 && data[j].split(" ")[data[j].split(" ").length - 1].toLowerCase().equals("the") || data[j].split(" ")[data[j].split(" ").length - 1].toLowerCase().equals("of"))
                    {
                        data[j] = data[j].substring(data[j].indexOf(" ") + 1, data[j].length()) + " " + data[j].split(" ")[0];
                    } else if(j == 5 || j == 6) {
                        if(data[j].length() >= 35) {
                            int inde = Cutils.findNearestChar(data[j], ' ', 35);
                            //out.println(data[j] + "\n" + inde);
                            
                            int[] coord2;

                            temp = new String[]{data[j].substring(0, inde), data[j].substring(inde, data[j].length())};
                            
                            if(!(continent.equals("South America") || continent.equals("Europe"))) {
                                coord2 = new int[]{Integer.parseInt(coord.split("`")[0]) - 650, Integer.parseInt(coord.split("`")[1]) + 80};
                            } else {
                                coord2 = new int[]{Integer.parseInt(coord.split("`")[0]) - 431, Integer.parseInt(coord.split("`")[1]) + 33};
                            }
                            data[j] = temp[0] + "!" + coord + "~" + temp[1] + "!" + coord2[0] + "`" + coord2[1] + "~";
                        
//                            out.println("\n\n" + Arrays.toString(temp) + country);
                        }
                    } else if(j == 9) {
                        temp = data[j].split("\u2587");
                        data[j] = "";
                        for(int ii = 0 ; ii < 3; ii++) {
                            int x = Integer.parseInt(coord.split("`")[0]), y = Integer.parseInt(coord.split("`")[1]);
                            if(!(continent.equals("South America") || continent.equals("Europe"))) {
                                data[j] += temp[ii] + "!" + (x + (315 * ii)) + "`" + y + "~";
                            } else {
                                data[j] += temp[ii] + "!" + (x + (187 * ii)) + "`" + y + "~";
                            }
                        }
                        // data[j] = data[j].split("\u2587")[0] + "! coord ~ " + data[j].split("\u2587")[1] + data[j].split("\u2587")[2]
                    } else if(j == 23) {
                        data[j] = data[j].replaceAll("[/dx]", "");
                        if(data[j].length() >= 30) {
                            int inde = Cutils.findNearestChar(data[j], ' ', 30);
                            //out.println(data[j] + "\n" + inde);
                            
                            temp = new String[]{data[j].substring(0, inde), data[j].substring(inde, data[j].length())};
                            
                            int[] coord2 = new int[]{Integer.parseInt(coord.split("`")[0]) - 397, Integer.parseInt(coord.split("`")[1]) + 55};
                            
                            data[j] = temp[0] + "!" + coord + "~" + temp[1] + "!" + coord2[0] + "`" + coord2[1] + "~";
                        
//                            out.println("\n\n" + Arrays.toString(temp) + country + "fixing national symbols");
                        }
                    }

                    construct += Cutils.swapChar('\u2587', ',', data[j]) + "!" + coord + "~";
                } catch( ArrayIndexOutOfBoundsException err )
                {
                    if(Cutils.swapChar('\u2587', ',', data[j]).length() != 0)
                        construct += " !1000`1000~";
                    else 
                        continue;
                }
            }
            output += construct + '\n';
        }
        FileWriter out = new FileWriter(new File("00Compiled Data.dat"));

        out.write(output);

        out.close();
        file.close();

        //File tester = new File("00Refined Data.dat");
        //tester.delete();

    }   

    private static int getContinet(String input){
        //out.println("comparing " + input);
        if(input.equals("North America"))
            return 0;
        else if(input.equals("South America"))
            return 1;
        else if(input.equals("Europe"))
            return 2;
        else if(input.equals("Asia"))
            return 3;
        else if(input.equals("Africa"))
            return 4;
        else if(input.equals("Australia"))
            return 5;
        else
            return -1;
    } 
}