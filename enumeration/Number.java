package enumeration;

/**
 * セルに入る数字です。
 *
 * @author Ryo Ito
 *
 */
public enum Number {

	/**
	 * インデックス１
	 */
	ONE(1),

	/**
	 * インデックス２
	 */
	TWO(2),

	/**
	 * インデックス３
	 */
	THREE(3),

	/**
	 * インデックス４
	 */
	FOUR(4),

	/**
	 * インデックス５
	 */
	FIVE(5),

	/**
	 * インデックス６
	 */
	SIX(6),

	/**
	 * インデックス７
	 */
	SEVEN(7),

	/**
	 * インデックス８
	 */
	EIGHT(8),

	/**
	 * インデックス９
	 */
	NINE(9);

	/**
	 * 入る数字です。
	 */
	private int number;

	/**
	 * コンストラクタです。
	 *
	 * @param number
	 */
	private Number(int number) {
		this.number = number;
	}

	/**
	 * 入る数字を取得します。
	 *
	 * @return 入る数字
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 指定したデータ値の数字を返します。
	 *
	 * @param number 指定データ値
	 * @return 数字
	 */
	public static Number of(int number) {
		for (Number enumuration : Number.values()) {
			if (enumuration.getNumber() == number) {
				return enumuration;
			}
		}
		return null;
	}

	/**
	 * 指定したデータ値の数字を返します。
	 *
	 * @param number 指定データ値
	 * @return 数字
	 */
	public static Number of(char number) {
		return Number.of(Character.getNumericValue(number));
	}

}
