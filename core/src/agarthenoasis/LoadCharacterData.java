package agarthenoasis;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 暫定対応としてデータファイル(csv)から各キャラクターのステータスを読み込む
 * 読み込んだ後はバイナリ形式のファイルとして保管する(重いので)
 * 読み込むステータスは以下の通り
 * 名前, 属性, HP, 攻撃, 防御, 速度, BP, スキル, UF(あれば), タクティクス, 騎士スキル(あれば), サポートスキル(あれば)
 * 将来的にはデータベース上に保管してリクエストを受けると適切なデータを返すようにする
 */
public class LoadCharacterData {
    private static final String URL = "https://github.com/AgarthenOasis/CharacterData/raw/master/";

    LoadCharacterData(final String dataFileName) {
        ObjectMapper mapper = new ObjectMapper();

    }

}
