package arrangement;

import java.util.List;

/**
 * 組み合わせです。　nＣx
 *
 * @author Ryo Ito
 *
 */
public class Combination {

	/**
	 * 組み合わせ処理を実行します。
	 *
	 */
	public static <T> void combine(List<T> n, int x, Aggregation a) {
		int[] variables = new int[x];
		combine(0, n.size() - x + 1, n.size(), x, a, variables);
	}

	/**
	 * 要素を抜き取って処理します。
	 */
	protected static void combine(int start, int end, int n, int x, Aggregation a, int... variables) {
		for (int i = start; i < end; i++) {
			variables[end - n + x - 1] = i;
			if (end == n) {
				a.execute(variables);
			} else {
				combine(i + 1, end + 1, n , x, a, variables);
			}
		}
	}
}
