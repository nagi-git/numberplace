package group;

import java.util.List;

import constants.NumberPlaceConstants;
import entity.Cell;
import enumeration.Number;

/**
 * 行です。
 *
 * @author Ryo Ito
 *
 */
public class Row extends Group {

	/**
	 * コンストラクタです。
	 *
	 * @param cells 入るセル
	 */
	public Row(List<Cell> cells) {
		super(cells);
	}

	/**
	 * 番号を算出します。
	 *
	 * @param cell セル
	 */
	public static Number calculateNumber(Cell cell) {
		return Number.of(cell.getLocation() / NumberPlaceConstants.CELL_NUMBER + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumber(Cell cell) {
		// 番号を設定
		this.number = calculateNumber(cell);
	}
}
