package NapolitanGame;

import java.io.*;
import java.util.*;

// items.txt 파일을 읽어서 Item 리스트를 생성하는 클래스
public class ItemLoader {

    // items.txt 파일을 읽어와서 List<Item>으로 반환
    public static List<Item> loadItems() throws IOException {
        List<Item> items = new ArrayList<>();

        // resources 폴더 내의 items.txt 파일을 읽어옴
        try (InputStream inputStream = ItemLoader.class.getResourceAsStream("/NapolitanGame/items.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                // 파일을 찾지 못한 경우 예외 발생
                throw new FileNotFoundException("items.txt 파일을 찾을 수 없습니다.");
            }

            String line;
            while ((line = reader.readLine()) != null) {
                // 한 줄씩 읽어서 Item 객체로 변환 후 리스트에 추가
                items.add(new Item(line.trim()));
            }
        }

        return items;
    }
}
