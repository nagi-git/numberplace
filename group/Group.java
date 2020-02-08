package group;

import java.util.List;

import entity.Cell;
import enumeration.Number;

/**
 * グループの抽象クラスです。
 *
 * @author Ryo Ito
 *
 */
public abstract class Group {

	/**
	 * 入るセルです。
	 */
	private List<Cell> cells;

	/**
	 * 番号です。
	 */
	protected Number number;

	/**
	 * コンストラクタです。
	 *
	 * @param cells 入るセル
	 */
	public Group(List<Cell> cells) {
		// 入るセルを設定
		this.cells = cells;
		// 番号を設定
		setNumber(cells.get(0));
	}

	/**
	 * 番号を取得します。
	 *
	 * @return 番号
	 */
	public Number getNumber() {
		return number;
	}

	/**
	 * 入る数字をセットします。
	 *
	 * @param cell 入る数字
	 */
	public abstract void setNumber(Cell cell);

	/**
	 * 入るセルをセットします。
	 *
	 * @param cells 入るセル
	 */
	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	/**
	 * 入るセルを取得します。
	 *
	 * @return 入るセル
	 */
	public List<Cell> getCells() {
		return cells;
	}
}
