import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import static java.lang.System.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Cutils {
  public static final char Barfilled = '\u2588', BarEmpty = ' ';
  public static final int BarSize = 20, tempp = 0;
  public static String[] DataPointsLocations, DataPoints, continentDataLocations, CountryAbbreviation;
  public static int[] DataOffsets;
  public static boolean EraseUnneededfiles;
  public static String FontFileLocation;
  private static boolean HasInternet = true;
  private static final String[] PrimaryFiles = { "Countries.dat", "config.txt", "CountryAbrivations.dat" };

  public static String[] addArrayindex(String[] array) {
    String[] output = new String[array.length + 1];
    for (int i = 0; i < array.length; i++)
      output[i] = array[i];
    return output;
  }

  public static int[] addArrayindex(int[] array) {
    int[] output = new int[array.length + 1];
    for (int i = 0; i < array.length; i++)
      output[i] = array[i];
    return output;
  }

  public static int[] remArrayindex(int[] array) {
    int[] output = new int[array.length - 1];
    for (int i = 0; i < array.length - 1; i++)
      output[i] = array[i];
    return output;
  }

  public static int[] remArrayindexF(int[] array) {
    int[] output = new int[array.length - 1];
    for (int i = 0; i < array.length - 1; i++)
      output[i] = array[i + 1];
    return output;
  }

  public static String[] remArrayindexF(String[] array) {
    String[] output = new String[array.length - 1];
    for (int i = 0; i < array.length - 1; i++)
      output[i] = array[i + 1];
    return output;
  }

  // its about as simple as it looks, it searches an array for the target string
  // and returns [-1, -1] if it is not found, else it returns this[array index
  // that contains target, index of target in previous index]
  public static int[] searcharray(String target, String[] array) {
    int[] arr = { -1, -1 };
    for (int i = 0; i < array.length; i++) {
      if (array[i].indexOf(target) != -1) {
        arr[0] = i;
        arr[1] = array[i].indexOf(target);
        return arr;
      }
    }
    return arr;
  }

  public static int[] multiParsei(String[] input) {
    int[] output = new int[input.length];
    for (int i = 0; i < output.length; i++)
      output[i] = Integer.parseInt(input[i]);
    return output;
  }

  public static String swapChar(char target, char swaper, String input) {
    String output = "";
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == target) {
        output += swaper;
        continue;
      }
      output += input.charAt(i);
    }
    return output;
  }

  public static void loadConfig() throws IOException {
    String[] temp = new String[0];
    int count = 0;
    Scanner file = new Scanner(new File("config.txt"));
    while (file.hasNextLine()) {
      temp = addArrayindex(temp);
      temp[count] = file.nextLine();
      count++;

    }

    file.close();

    // out.println(Arrays.toString(temp));
    // out.println(temp[temp.length - 1]);
    // out.println(searcharray("EraseUnnee"))

    EraseUnneededfiles = Integer.parseInt(temp[searcharray("EraseUnneededfiles", temp)[0]].split(";")[1]) == 1 ? true
        : false;
    DataOffsets = multiParsei(temp[searcharray("DataOffsets", temp)[0]].split(";")[1].split(","));
    DataPoints = temp[searcharray("DataPoints;", temp)[0]].split(";")[1].split(",");
    FontFileLocation = temp[searcharray("FontFileLocation", temp)[0]].split(";")[1];
    continentDataLocations = temp[searcharray("ContinentDataLocations", temp)[0]].split(";")[1].split("/");
    // out.println(Arrays.toString(continentDataLocations));
    // out.println(DataPoints.length + "DATA POINTS LENGTH");
    // hi cole, burger ples. -annabell

    // here we are loading the config for CountryAbbreviation because most of the
    // html pages dont have the country abbreviatoins

    file = new Scanner(new File("CountryAbrivations.dat"));
    int max = Integer.parseInt(file.nextLine());
    CountryAbbreviation = new String[max];

    for(int i = 0; i < max; i++) {
      CountryAbbreviation[i] = file.nextLine();
    }

    file.close();

    // out.println(Arrays.toString(CountryAbbreviation));

  }

  public static void checkFileIntegrity() throws IOException {
    FileWriter filew;
    String fileData;
    for (String data : PrimaryFiles) {
      switch (data) {
        case "Countries.dat":
          fileData = "213~:North America~Anguilla~Antigua and Barbuda~Aruba~Bahamas the~Barbados~Belize~Bermuda~British Virgin Islands~Canada~Cayman Islands~Cuba~Curacao~Dominica~Dominican Republic~El Salvador~Grenada~Greenland~Guatemala~Haiti~Honduras~Jamaica~Mexico~Montserrat~Navassa Island~Nicaragua~Panama~Puerto Rico~Saint Barthelemy~Saint Kitts and Nevis~Saint Lucia~Saint Martin~Saint Vincent and the Grenadines~Sint Maarten~Trinidad and Tobago~Turks and Caicos Islands~United States~Virgin Islands~:South America~Argentina~Bolivia~Brazil~Chile~colombia~Ecuador~Guyana~Paraguay~Peru~Suriname~Uruguay~Venezuela~:Europe~Albania~Andorra~Austria~Belarus~Belgium~Bosnia and Herzegovina~Bulgaria~Croatia~Cyprus~Czechia~Denmark~Estonia~Finland~France~Greece~Germany~Hungary~Iceland~Ireland~Italy~Kosovo~Latvia~liechtenstein~Lithuania~Luxembourg~North Macedonia~Malta~Moldova~Monaco~Montenegro~Netherlands~Norway~Poland~Portugal~Romania~San Marino~Serbia~Slovakia~Slovenia~Spain~Sweden~Switzerland~Ukraine~United Kingdom~:Asia~Armenia~Azerbaijan~Bahrain~Georgia~Iran~Iraq~Israel~Jordan~Kuwait~Lebanon~Oman~Qatar~Saudi Arabia~Syrida~Turkey turkiye~United Arab Emirates~Yemen~Kazakhstan~Kyrgyzstan~Russia~Tajikistan~Turkmenistan~Uzbekistan~Afghanistan~Bangladesh~Bhutan~India~Maldives~Nepal~Pakistan~Sri Lanka~Brunei~Burma~Cambodia~China~Hong Kong~Indonesia~Japan~Korea North~Korea South~Laos~Macau~Malaysia~Mongolia~Philippines~Singapore~Taiwan~Thailand~Timor-Leste~Vietnam~:Africa~Algeria~Angola~Benin~Botswana~Burkina Faso~Burundi~Cameroon~Cabo Verde~Central African Republic~Chad~Comoros~congo Democratic Republic of the~Congo Republic of the~Cote Divoire~Djibouti~Egypt~Equatorial Guinea~Eritrea~Ethiopia~Gabon~Gambia The~Ghana~Guinea~Guinea-Bissau~Kenya~Lesotho~Liberia~Libya~Madagascar~Malawi~Mali~Mauritania~Mauritius~Mayotte~Morocco~Mozambique~Namibia~Niger~Nigeria~Rwanda~SaoTome and Principe~Senegal~Seychelles~Sierra Leone~Somalia~South Africa~SouthSudan~Sudan~Swaziland~Tanzania~Togo~Tunisia~Uganda~WesternSahara~Zambia~Zimbabwe~:Australia~Australia~Fiji~Kiribati~MarshallIslands~Micronesia Federated States Of~Nauru~New Zealand~Palau~Papua NewGuinea~Samoa~Solomon Islands~Tonga~Tuvau~Vanuatu";

          //fileData = "37~:North America~Anguilla~Antigua and Barbuda~Aruba~Bahamas the~Barbados~Belize~Bermuda~British Virgin Islands~Canada~Cayman Islands~Cuba~Curacao~Dominica~Dominican Republic~El Salvador~Grenada~Greenland~Guatemala~Haiti~Honduras~Jamaica~Mexico~Montserrat~Navassa Island~Nicaragua~Panama~Puerto Rico~Saint Barthelemy~Saint Kitts and Nevis~Saint Lucia~Saint Martin~Saint Vincent and the Grenadines~Sint Maarten~Trinidad and Tobago~Turks and Caicos Islands~United States~Virgin Islands";
          // out.println(fileData.split("~").length);
          break; // 0 1 2 3 4 5 6 7 8 9 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2
        case "config.txt": // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 0 1 2 3 4 5
                           // 6 7 8 9 0 1 2 3 4 5 6 7 name location coords area border climate terrain
                           // lowpoi higpoi 3natres nation lang reli pop popGR BR DR LifeE
          fileData = "DataPoints;Location,Geographic coordinates,Area</a,border countries (,Climate,terrain,lowest point,highest point,Natural resources,Nationality,languages,Religions,Population</a,Population growth rate,Birth rate,Death rate,Net migration rate,Life expectancy at birth,Country Abbreviation,Government type,Capital,Legal system,National symbol(s),Industries,Exports - commodities,Imports - commodities,agricultural land:,forest:,Irrigated land~DataOffsets;4,4,6,2,4,5,2,2,4,6,5,4,4,4,4,4,4,6,0,4,6,4,4,4,4,4,4,6,4,2,4~EraseUnneededfiles;0~FontFileLocation;C:\\Users\\ahhhhhhhhhhhhhhhhhhh\\Desktop\\autocountrypages\\Font~ContinentDataLocations;600`200,1925`310,2300`375,1965`440,2125`500,2325`565,2335`685,2025`820,2025`880,2200`945,575`1655,585`1715,545`1782,573`1897,867`1953,554`2011,583`2069,0`0,429`2313,2029`1515,1909`1568,1642`1620,1741`1673,1842`1729,1699`1890,1697`1997,1779`2103,1720`1130,1720`1190,1720`1291,0`0/372`254,1290`308,1543`351,1394`394,1418`432,1555`474,1569`559,1357`640,1360`680,1490`721,397`1185,404`1223,379`1263,395`1334,592`1375,377`1415,408`1454,306`1593,0`0,1357`1091,1277`1124,1097`1157,1163`1199,1230`1228,1137`1335,1134`1403,1191`1476,1130`850,1130`890,1130`937,0`0/326`152,1285`203,1548`251,1327`290,1426`336,1552`377,1574`464,1366`551,1364`590,1499`638,397`1116,405`1165,379`1208,403`1287,596`1327,379`1366,409`1402,0`0,286`1556,1362`1025,1278`1060,1098`1102,1169`1133,1240`1171,1149`1280,1143`1355,1193`1424,1103`780,1103`823,1105`882,0`0/545`208,1910`302,2299`366,1943`424,2123`497,2314`567,2338`691,2013`816,2022`889,2217`947,567`1676,582`1737,542`1804,567`1922,858`1983,548`2050,579`2105,0`0,448`2329,2019`1536,1904`1591,1645`1639,1734`1697,1852`1755,1689`1922,1686`2034,1757`2145,1681`1174,1681`1238,1688`1308,0`0/501`255,1914`340,2304`404,1965`472,2115`540,2306`601,2336`733,2018`854,2026`921,2224`983,577`1709,588`1770,545`1833,570`1948,863`2012,551`2070,587`2130,0`0,440`2354,2013`1562,1896`1620,1629`1671,1728`1727,1829`1780,1682`1947,1682`2059,1772`2161,1682`1183,1682`1255,1682`1338,0`0/568`299,1949`379,2326`442,1984`508,2135`572,2329`634,2366`758,2036`886,2054`953,2240`1014,592`1729,607`1784,570`1847,591`1958,886`2021,580`2070,613`2133,0`0,468`2362,2037`1587,1921`1640,1652`1694,1753`1744,1851`1801,1713`1960,1717`2062,1784`2178,1705`1205,1708`1282,1710`1360,0`0";
          break; // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
        case "CountryAbrivations.dat":
          fileData = "213~Al~AG~AW~BS~BB~BZ~BM~VG~CA~KY~CU~CW~DM~DO~SV~GD~GL~GT~HT~HN~JM~MX~MS~NI~NI~PA~PR~BL~KN~LC~MF~VC~SM~TT~TC~US~VI~AR~BO~BR~CL~CO~EC~GY~PY~PE~SR~UY~VE~AL~AD~AU~BY~BU~BA~BG~HR~CY~CZ~DK~EE~FI~FR~GR~HU~HU~IS~IE~IT~KO~LV~LI~LT~LU~NM~MT~MD~MC~ME~NL~NO~PL~PT~RO~SM~RS~SK~SI~ES~SE~CH~UA~GB~AM~AZ~BH~GE~IR~IQ~IL~JO~KW~LB~OM~QA~SA~SY~TR~AE~YE~KZ~KG~RU~TJ~TM~UZ~AF~BD~BT~IN~MV~NP~PK~LK~BI~BU~KH~CN~HK~ID~JP~KR~KP~LS~MO~MY~MN~PH~SG~TW~TH~TL~VN~DZ~AO~BJ~BW	~BF~BI~CM~CV~CF~TD~KM~CD~CG~CI~DJ~EG~GQ~ER~ET~GA~GM~GH~GN~GW~KE~LS~LR~LY~MG~MW~ML~MR~MU~YT~MA~MZ~NA~NE~NG~RW~ST~SN~SC~SL~SO~ZA~SS~SD~SZ~TZ~TG~TN~UG~EH~ZM~ZW~AU~FJ~KI~MH~FM~NR~NZ~PW~PG~WS~SB~TO~TV~VU"; // /
          break;
        default:
          continue;
      }

      // out.println(fileData + "\n\n");
      if (!new File(data).exists()) {
        filew = new FileWriter(new File(data));
        filew.write(swapChar('~', '\n', fileData));
        filew.close();
      }
    }
    
    /*String[] blankPages = {"North America.png", "South America.png", "Europe.png", "Asia.png", "Africa.png", "Australia.png"};



    JarFile file = new JarFile(Terminal.ProgramName);
    
    Enumeration<JarEntry> entries = file.entries();
    while(entries.hasMoreElements()) {
      String data = "";
      JarEntry entry = entries.nextElement();
      data = entry.toString();
      for(String item : blankPages) {
        if(item.equals(data)){
          int readBytes;
          FileWriter fw = new FileWriter(new File(item));
          InputStream is = file.getInputStream(entry);

            BufferedWriter output = new BufferedWriter(fw);
                 while ((readBytes = is.read() ) != -1) {
                    output.write((char) readBytes);
                 }

          is.close();
          output.close();
          fw.close();
          out.println(data);
          continue;
        }
      }
    }

    file.close();*/
  }

  public static void inject_Data() throws IOException {
    // read in the file
    // open the file from file[index].split("-")[0];
    // inject new data at the end of it and close the file
    Scanner in = new Scanner(new File("Inject.txt"));
    FileWriter fout;
    String[] tmpa = new String[0];
    String holder = "";
  //  int count = 0;

    while (in.hasNextLine()) {
      holder = in.nextLine();
      out.println("Injecting data " + holder.split("!")[1].split(">")[0] + " into the file " + holder.split("!")[0].toLowerCase());

      if (!new File(holder.split("!")[0]).exists()) {
        FileWriter temp = new FileWriter(holder.split("!")[0].toLowerCase());
        temp.write("filler\nfiller\nfiller\nfiller");
        temp.close();
      }

      /* file = new Scanner(new File(holder.split("!")[0].toLowerCase()));

      while (file.hasNextLine()) {
        tmpa = Cutils.addArrayindex(tmpa);
        tmpa[count] = file.nextLine();
        count++;
      }
      file.close(); */

      tmpa = Cutils.addArrayindex(tmpa);
      tmpa[tmpa.length - 1] = holder.split("!")[1];

      fout = new FileWriter(new File(holder.split("!")[0].toLowerCase()), true); // true activates the append mode so that is only adds to the file without erasing its contents

      for (int i = 0; i < tmpa.length - 1; i++) {
        fout.write(tmpa[i]);
      }
      fout.close();

    }

  }

  public static boolean writeInfoToPage(String data, String font) throws IOException {

    // out.println(Arrays.toString(data.split("\u2588")));

    String cpage = data.split("\u2588")[0], country = data.split("\u2588")[1];
    String[] datf = data.split("\u2588")[2].split("~");
    String temp = cpage + ".png";

    JarFile file = new JarFile(Terminal.ProgramName);    
    Enumeration<JarEntry> entries = file.entries();
    JarEntry entry = entries.nextElement();
    while(entries.hasMoreElements()){
      
        if(temp.equals(entry.toString())){
          break;
        }
      entry = entries.nextElement();
    }
    BufferedImage page = ImageIO.read(file.getInputStream(entry));
    file.close();

    

    Graphics g = page.getGraphics();

    g.setColor(new Color(0x251607)); // closest color to pencil marking
  //  g.setColor(new Color(0x8b8a85)); // my pencil marking


    //g.setColor(new Color(0xFF0000));

    // out.println(Arrays.toString(datf) + " DATF HERE
    // -----------------------------------------------------------");
    for (int i = 0; i < datf.length; i++) {

      if(!(cpage.equals("Europe") || cpage.equals("South America"))){
        if (i == 0) // if the text will be a title, do this 
          g.setFont(new Font(font, Font.BOLD, 80 - (int) Math.floor(Math.random() * (5 - -5 + 1) + -5)));
        else // text will be smaller than a title
          g.setFont(new Font(font, Font.BOLD, 55 - (int) Math.floor(Math.random() * (5 - -5 + 1) + -5)));
      } else {

        if (i == 0) // if the text will be a title, do this 
          g.setFont(new Font(font, Font.BOLD, 40 - (int) Math.floor(Math.random() * (5 - -5 + 1) + -5)));
        else // text will be smaller than a title
          g.setFont(new Font(font, Font.BOLD, 35 - (int) Math.floor(Math.random() * (5 - -5 + 1) + -5)));
      }
      // Integer.parseInt(data.split("~")[1].split("`")[0]);
      // data is like "text"!x`y~"text"!x`y~"text"!x`y
      // out.println("Writing " + datf[i].split("!")[0] + " to " + country);
      //out.println(datf[i]);
      g.drawString(datf[i].split("!")[0], Integer.parseInt(datf[i].split("!")[1].split("`")[0]), Integer.parseInt(datf[i].split("!")[1].split("`")[1]));
      // g.drawString(datf[i].split("~")[0],
      // Integer.parseInt(datf[i].split("~")[1].split("`")[0]),
      // Integer.parseInt(datf[i].split("~")[1].split("`")[1]));
    }
    
      g.setFont(new Font("Calibri", Font.BOLD, 50));
      if(cpage.equals("Europe") || cpage.equals("South America")){
        g.setColor(new Color(0xff0000));
      }else{
        g.setColor(new Color(0xff0000));
      }
    
    // Continent-country.png
    ImageIO.write(page, "png", new File(cpage + "-" + country + ".png"));
    //out.println("Wrote page for " + country);
    return true;
  
  }

  public static boolean gitWebpage(String WebUrl, String filename) throws IOException {
    try {
      new File(filename.toLowerCase() + ".html");
      URL url = new URL(WebUrl);
      BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));

      BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".html"));

      String line;
      while ((line = readr.readLine()) != null) {
        writer.write(line);
      }

      readr.close();
      writer.close();

    } catch (MalformedURLException mue) {
      Terminal.ErrorLog.add("Malformed URL Exception raised on " + WebUrl);
      return false;
    } catch (FileNotFoundException fnf) {
      Terminal.ErrorLog.add("Error on " + filename + ", file was not found! Line : " + (tempp + 1));
      return false;
    } catch (UnknownHostException noinet) {
      if (HasInternet)
        Terminal.ErrorLog.add(
            "/ / / / / / / / / / / / / / / / / / /\n / /Error in GitInfo, No internet/ /\n/ / / / / / / / / / / / / / / / / / /\n");
      HasInternet = false;
      return false;
    }
    return true;
  }

  public static boolean CleanFiles() throws IOException {
    String[] delLog;
    Scanner file = new Scanner(new File("Countries.dat"));
    int size = Integer.parseInt(file.nextLine()) * 2, i = 0;
    delLog = new String[size + 6];
    String holder = "", marker = "";

    while (i < size) {
      try {
        holder = file.nextLine();
      } catch (NoSuchElementException err) {
        
        // err.printStackTrace();
      }

      if (holder.charAt(0) == ':') {
        marker = holder.substring(1, holder.length());
        continue;
      }
      delLog[i] = holder.toLowerCase() + ".html"; // uncomment to have the clean files button remove unneeded html files
      delLog[i + 1] = marker + "-" + holder + ".png";
      i += 2;
      // delLog[i] = marker + "-" + holder + ".png";
      // i += 1;
    }
    i -= 2;
    delLog[++i] = "00Compiled Data.dat";
    delLog[++i] = "00Refined Data.dat";
    delLog[++i] = "config.txt";
    delLog[++i] = "Countries.dat";
    delLog[++i] = "CountryAbrivations.dat";
    delLog[++i] = "CountryRawData.dat";
    delLog[++i] = "Inject.txt";
    
    File opener;

    for (i = 0; i < delLog.length; i++) {
      out.println(delLog[i]);
      opener = new File(delLog[i]);
      
      if (opener.exists())
        opener.delete();
    }

    file.close();

    return true;

  }

  public static String[] AppendList(String[] list1, String[] list2) {
    int count = 0;
    String[] output = new String[list1.length + list2.length];

    if(list1.length > 0) 
      while(count <= list1.length) {
        output[count] = list1[count];
        count++;
      }

    while(count < list1.length + list2.length) {
      output[count] = list2[count - list1.length];
      count++;
    }

    return output;
  }

  public static int findNearestChar(String str, char target, int startPoint) {
    int index = -1;
    int[] distance = new int[]{str.lastIndexOf(target, startPoint), str.indexOf(target, startPoint)};

    if(distance[0] == -1) {
      distance[0] = distance[1];
    } else if(distance[1] == -1) {
      distance[1] = distance[0];
    }
    
    if(distance[0] > distance[1]) {
      index = distance[0];
    } else if(distance[0] < distance[1]) {
      index = distance[0];
    } else if(distance[0] == distance[1]) {
      index = distance[0];
    }
//    out.println(index + "in Cutils.findnearestChar()");
    return index;
  }

  public static byte[] strtobyte(String input) {
    byte[] output = new byte[input.split(",").length];
    int count = 0;

    for(String item : input.split(",")) {

      output[count++] = Byte.parseByte(item);
    }

    return output;
  }
}