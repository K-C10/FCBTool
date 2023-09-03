import static java.lang.System.*;

public class Progress {
  int barLength;
  char Empty, filled;
  String bar = "", oldbar;
  boolean mid;

  public Progress() {
    barLength = 20;
    Empty = '-';
    filled = '#';
  }

  public Progress(int L) {
    barLength = L;
    Empty = '-';
    filled = '#';
  }

  public Progress(int L, char fi, char ep) {
    barLength = L;
    Empty = ep;
    filled = fi;
    mid = false;
  }

  public void update(int done, int total) {
    oldbar = bar;
    String output = "[";
    
    int percentdone = (done * 100) / total;
    for (int x = 0; x < percentdone / (barLength / 4); x++)
      output += filled;
    for (int x = 0; x < barLength - percentdone / (barLength / 4); x++) {
      output += Empty;
    }
    output += "] %" + percentdone;
    if(percentdone == 100) output += " Done!\n---------------------------------\n";

    bar = output;
    if(!oldbar.equals(bar)){
      for (int i = 0; i < oldbar.length(); i++)
        out.print('\b');
      out.print(bar);
    }
  } 
}