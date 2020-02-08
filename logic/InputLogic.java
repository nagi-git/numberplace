package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import constants.NumberPlaceConstants;
import entity.Cell;
import enumeration.Number;

/**
 * 数字入力のロジック
 *
 * @author Ryo Ito
 *
 */
public class InputLogic {

	/**
	 * 入力した数字
	 */
	private String buff;

	/**
	 * ユーザに数字を入力させます。
	 *
	 */
	public void inputNumber() {
		// メッセージを表示
		System.out.println(NumberPlaceConstants.MESSAGE_INPUT);

		String readNumber = "";
		for (int i = 0; i < NumberPlaceConstants.CELL_NUMBER; i++) {
			// 数字を入力
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			try {
				input = br.readLine();
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			readNumber = readNumber.concat(input);
		}
		buff = readNumber;
	}

	/**
	 * 入力した数字をマップに変換します。
	 *
	 */
	public Map<Integer, Cell> numberToEntityList() {
		// 全セルマップ
		Map<Integer, Cell> cellMap = new HashMap<>();
		// 入力する全セル数
		int allCellNumber = NumberPlaceConstants.CELL_NUMBER * NumberPlaceConstants.CELL_NUMBER;
		for (int location = 0; location < allCellNumber; location++) {
			// 文字を取得
			char number = this.buff.charAt(location);
			// 入力した数値が０か０以外で場合分け
			if (number == '0') {
				cellMap.put(location, new Cell(location));
			} else {
				cellMap.put(location, new Cell(Number.of(number), location));
			}
		}
		return cellMap;
	}

}
