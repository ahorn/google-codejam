import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Solve Google Code Jam Qualification Problem C (2011)
 * 
 * @see http://code.google.com/codejam/contest/dashboard?c=975485#s=p2
 * 
 * @author A. Horn
 */
public class RunCandy {

  private final BufferedReader reader;

  RunCandy(String filename) {
    final InputStream stream = getClass().getResourceAsStream(filename);
    reader = new BufferedReader(new InputStreamReader(stream), 700000);
  }

  public static void main(String[] args) {
    RunCandy r = new RunCandy("input");

    try {
      r.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  void run() throws Exception {
    int t = readInt();
    int n;
    int[] vs;
    for (int i = 1; i <= t; i++) {
      n = readInt();
      vs = readInts(n);
      solve(i, vs);
    }
  }

  /**
   * Compute the maximum candy value of Sean's candy pile without making his
   * brother Patrick cry. If this is impossible, print NO.
   * 
   * @param t
   *          test case number
   * @param vs
   *          candy values (duplicates are possible)
   */
  private void solve(int t, int[] vs) {
    Arrays.sort(vs);

    final int total = xor(vs, 0, vs.length);
    if (total != 0) {
      out.println("Case #" + t + ": NO");
      return;
    }

    int sum = 0;
    for (int i = 1; i < vs.length; i++) {
      sum += vs[i];
    }

    out.println("Case #" + t + ": " + sum);
  }

  int xor(int[] vs, int a, int b) {
    int result = 0;
    for (int i = a; i < b; i++) {
      result ^= vs[i];
    }
    return result;
  }

  private int readInt() throws Exception {
    return Integer.valueOf(reader.readLine());
  }

  private int[] readInts(int n) throws Exception {
    String line = reader.readLine();
    String[] chunks = line.split(" ");
    if (chunks.length != n) {
      throw new AssertionError("Unexpected number of chunks");
    }

    int[] ints = new int[n];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = Integer.valueOf(chunks[i]);
    }

    return ints;
  }

}
