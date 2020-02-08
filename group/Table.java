package group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constants.NumberPlaceConstants;
import entity.Cell;

/**
 * テーブルです。
 *
 * @author Ryo Ito
 *
 */
public class Table {

	/**
	 * セルのマップです。
	 */
    private Map<Integer, Cell> cellMap;

	/**
	 * コンストラクタです。
	 *
	 * @param cellMap 全セルのマップ
	 */
	public Table(Map<Integer, Cell> cellMap) {
		this.cellMap = cellMap;
	}

	/**
	 * 全セルマップをセルリストにします。
	 *
	 * @param cellMap 全セルマップ
	 * @return 全てのセルリスト
	 */
	public List<Cell> allCells() {
		List<Cell> allCellLists = new ArrayList<>(cellMap.values());
		return allCellLists;
	}

	/**
	 * 全セルマップを行毎のセルリストに分割します。
	 *
	 * @param cellMap 全セルマップ
	 * @return 行毎のセルリスト
	 */
	public List<Row> rowGroup() {
		List<Row> rowList = new ArrayList<>();
		for (int i = 0; i < NumberPlaceConstants.CELL_NUMBER; i++) {
			// 行毎のリストを作成
			List<Cell> cellList =  new ArrayList<>();

			for (int j = 0; j < NumberPlaceConstants.CELL_NUMBER; j++) {
				// 取り出すセルの位置
				int location = i * NumberPlaceConstants.CELL_NUMBER + j;
				cellList.add(cellMap.get(location));
			}
			rowList.add(new Row(cellList));
		}
		return rowList;
	}

	/**
	 * 全セルマップを列毎のセルリストに分割します。
	 *
	 * @param cellMap 全セルマップ
	 * @return 列毎のセルリスト
	 */
	public List<Column> columnGroup() {
		List<Column> columnList = new ArrayList<>();
		for (int i = 0; i < NumberPlaceConstants.CELL_NUMBER; i++) {
			// 列毎のリストを作成
			List<Cell> cellList =  new ArrayList<>();

			for (int j = 0; j < NumberPlaceConstants.CELL_NUMBER; j++) {
				// 取り出すセルの位置
				int location = i + j * NumberPlaceConstants.CELL_NUMBER;
				cellList.add(cellMap.get(location));
			}
			columnList.add(new Column(cellList));
		}
		return columnList;
	}

	/**
	 * 全セルマップをブロック毎のセルリストに分割します。
	 *
	 * @param cellMap 全セルマップ
	 * @return ブロック毎のセルリスト
	 */
	public List<Block> blockGroup() {
		List<Block> blockList = new ArrayList<>();
		for (int i = 0; i < NumberPlaceConstants.BLOCK_NUMBER; i++) {
			for (int j = 0; j < NumberPlaceConstants.BLOCK_NUMBER; j++) {
				// ブロック毎のリストを作成
				List<Cell> cellList =  new ArrayList<>();
				for (int k = 0; k < NumberPlaceConstants.BLOCK_NUMBER; k++) {
					for (int l = 0; l < NumberPlaceConstants.BLOCK_NUMBER; l++) {
						// 取り出すセルの位置
						int location = i * NumberPlaceConstants.CELL_NUMBER * NumberPlaceConstants.BLOCK_NUMBER
								+ j * NumberPlaceConstants.BLOCK_NUMBER
								+ k * NumberPlaceConstants.CELL_NUMBER
								+ l;
						cellList.add(cellMap.get(location));
					}
				}
				blockList.add(new Block(cellList));
			}
		}
		return blockList;
	}

	/**
	 * 全セルを埋め終わったかどうかを返します。
	 *
	 * @return 全セルを埋め終わったかどうか
	 */
	public boolean isFinished() {
		for (Cell cell : cellMap.values()) {
			// 入る数字が未確定の場合
			if (cell.getNumber() == null) {
				// まだ埋め終わっていない
				return false;
			}
		}
		return true;
	}
}
