package entity;

import java.util.HashMap;
import java.util.Map;

import enumeration.Number;

/**
 * セルです。
 *
 * @author Ryo Ito
 *
 */
public class Cell {

	/**
	 * 入る数字です。
	 */
	private Number number;

	/**
	 * 入る可能性がある数字のマップです。
	 */
	private Map<Number, Boolean> possibilityMap = new HashMap<>();
		{
			possibilityMap.put(Number.ONE, true);
			possibilityMap.put(Number.TWO, true);
			possibilityMap.put(Number.THREE, true);
			possibilityMap.put(Number.FOUR, true);
			possibilityMap.put(Number.FIVE, true);
			possibilityMap.put(Number.SIX, true);
			possibilityMap.put(Number.SEVEN, true);
			possibilityMap.put(Number.EIGHT, true);
			possibilityMap.put(Number.NINE, true);
		}

	/**
	 * セルの位置です。
	 */
	private int location;

	/**
	 * 新規に決定した数字かどうかのフラグです。
	 */
	private boolean isNew;

	/**
	 * コンストラクタです。
	 *
	 * @param location セルの位置
	 */
	public Cell(int location) {
		// 入る数字を設定
		this.number = null;
		// 入る数字を設定
		this.isNew = true;
		// セルの位置を設定
		this.location = location;
	}

	/**
	 * コンストラクタです。
	 *
	 * @param number 入る数字
	 * @param location セルの位置
	 */
	public Cell(Number number, int location) {
		// 入る数字を設定
		setNumber(number);
		// 入る数字を設定
		this.isNew = false;
		// セルの位置を設定
		this.location = location;
	}

	/**
	 * 入る数字を取得します。
	 *
	 * @return 入る数字
	 */
	public Number getNumber() {
		return number;
	}

	/**
	 * 入る数字をセットします。
	 *
	 * @param number 入る数字
	 */
	public void setNumber(Number number) {
		this.number = number;
		// １～９まで、確定数字以外が入力される可能性は全てなし
		for (Number possibilityNumber : this.possibilityMap.keySet()) {
			if (possibilityNumber != number) {
				isImpossibile(possibilityNumber);
			}
		}
	}

	/**
	 * 数字が入る可能性のマップを取得します。
	 *
	 * @return 数字が入る可能性のマップ
	 */
	public Map<Number, Boolean> getPossibilityMap() {
		return this.possibilityMap;
	}

	/**
	 * 指定した数字が入る可能性をなくします。
	 *
	 * @return 数字が入る可能性のマップ
	 */
	public void isImpossibile(Number number) {
		this.possibilityMap.put(number, false);
	}

	/**
	 * セルの位置を取得します。
	 *
	 * @return セルの位置
	 */
	public int getLocation() {
		return this.location;
	}

	/**
	 * 指定した数字が入る可能性があるかどうかを取得します。
	 *
	 * @param number 指定数字
	 * @return 指定数字が入る可能性
	 */
	public boolean getPossibility(Number number) {
		return this.possibilityMap.get(number);
	}

	/**
	 * 新規に決定した数字かどうかを返します。
	 *
	 * @return 新規に決定した数字かどうか
	 */
	public boolean isNew() {
		return isNew;
	}

	/**
	 * 新規に決定した数字かをセットします。
	 *
	 * @param isNew 新規に決定した数字かどうか
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
