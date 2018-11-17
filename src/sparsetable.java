import java.util.*;
import java.io.*;
public class sparsetable {
	
	static int MAXN = 10005, k = 0;
	static int minArr[][] = new int[MAXN][16], maxArr[][] = new int[MAXN][16];
	
	static int min(int x, int y) {
		return x <= y ? x : y;
	}
	
	static int max(int x, int y) {
		return x >= y ? x : y;
	}
	
	static void build(int N, int arr[]) {
		for(int i=0;i<N;i++) {
			minArr[i][0] = arr[i]; maxArr[i][0] = arr[i];
		}
		
		for(int i=1;i<=15;i++) {
			for(int j=0;j+(1<<(i-1))<N;j++) {
				minArr[j][i] = min(minArr[j][i-1],minArr[j+(1<<(i-1))][i-1]);
				maxArr[j][i] = max(maxArr[j][i-1],maxArr[j+(1<<(i-1))][i-1]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int N = readInt(), Q = readInt();
		int arr[] = new int[N];
		for(int i=0;i<N;i++) arr[i] = readInt();
		build(N, arr);
		for(int i=0;i<Q;i++) {
			int a = readInt()-1, b = readInt()-1;
			int k = (int)(Math.log(b-a+1)/Math.log(2));
			pr.println(max(maxArr[a][k], maxArr[b-(1<<k)+1][k]) - min(minArr[a][k], minArr[b-(1<<k)+1][k]));
		}
		pr.close();
	}

	final private static int BUFFER_SIZE = 1 << 16;
	private static DataInputStream din = new DataInputStream(System.in);
	private static byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static String readLine() throws IOException {
		byte[] buf = new byte[64]; // line length
		int cnt = 0, c;
		while ((c = Read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}
	public static String read() throws IOException{
		byte[] ret = new byte[1024];
        int idx = 0;
        byte c = Read();
        while (c <= ' ') {
            c = Read();
        }
        do {
            ret[idx++] = c;
            c = Read();
        } while (c != -1 && c != ' ' && c != '\n' && c != '\r');
        return new String(ret, 0, idx);
	}
	public static int readInt() throws IOException {
		int ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (neg)
			return -ret;
		return ret;
	}

	public static long readLong() throws IOException {
		long ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public static double readDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = Read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}

		if (neg)
			return -ret;
		return ret;
	}

	private static void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if (bytesRead == -1)
			buffer[0] = -1;
	}

	private static byte Read() throws IOException {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}

	public void close() throws IOException {
		if (din == null)
			return;
		din.close();
	}

	static void print(Object o) {
		pr.print(o);
	}

	static void println(Object o) {
		pr.println(o);
	}

	static void flush() {
		pr.flush();
	}

	static void println() {
		pr.println();
	}

	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}