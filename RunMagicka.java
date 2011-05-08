import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solve Google Code Jam Qualification Problem B (2011).
 * 
 * @see http://code.google.com/codejam/contest/dashboard?c=975485#s=p1
 * 
 * @author A. Horn
 */
public class RunMagicka {

  private final BufferedReader reader;

  /**
   * Stack of letters
   */
  private Deque<Character> stack;

  /**
   * Previous letter
   */
  char prev = 0;

  RunMagicka(String filename) {
    final InputStream stream = getClass().getResourceAsStream(filename);
    reader = new BufferedReader(new InputStreamReader(stream), 700000);
  }

  public static void main(String[] args) {
    RunMagicka r = new RunMagicka("input");

    try {
      r.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void run() throws Exception {
    final int t = Integer.valueOf(reader.readLine());

    Map<String, Character> cm;
    Set<CharPair> ds;
    String line;
    String chunks[];
    int c;
    int d;
    int n;
    for (int i = 1; i <= t; i++) {
      line = reader.readLine();
      chunks = line.split(" ");
      c = Integer.valueOf(chunks[0]);
      d = Integer.valueOf(chunks[c + 1]);
      n = Integer.valueOf(chunks[c + d + 2]);

      cm = new HashMap<String, Character>(2 * c);
      char[] combs;
      for (int j = 1; j <= c; j++) {
        combs = chunks[j].toCharArray();
        cm.put(combs[0] + "" + combs[1], combs[2]);
        cm.put(combs[1] + "" + combs[0], combs[2]);
      }

      ds = new HashSet<CharPair>(d);
      for (int j = c + 2; j <= c + d + 1; j++) {
        ds.add(new CharPair(chunks[j].charAt(0), chunks[j].charAt(1)));
      }

      solve(i, cm, ds, n, chunks[c + d + 3].toCharArray());
    }
  }

  void solve(int t, Map<String, Character> cm, Set<CharPair> ds, int n, char[] letters) {
    stack = new ArrayDeque<Character>(n);
    prev = 0;
    
    Character combine;
    boolean del;
    for (char letter : letters) {
      if (prev != 0) {
        combine = cm.get(prev + "" + letter);
        if (combine == null) {
          push(letter);

          del = false;
          for (CharPair p : ds) {
            if (stack.contains(p.a) && stack.contains(p.b)) {
              del = true;
              break;
            }
          }

          if (del) {
            clear();
          }
        } else {
          pop();
          push(combine);
        }
      } else {
        push(letter);
      }
    }

    out.println("Case #" + t + ": " + stack.toString());
  }

  private void push(char e) {
    stack.add(e);
    prev = e;
  }

  private void pop() {
    stack.removeLast();
  }

  private void clear() {
    stack.clear();
    prev = 0;
  }

  class CharPair {
    char a;
    char b;

    CharPair(char a, char b) {
      this.a = a;
      this.b = b;
    }
  }

}
