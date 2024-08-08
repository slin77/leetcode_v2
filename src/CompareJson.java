import java.util.HashMap;
import java.util.Objects;

// It's like "
// {
// "name": "John", //pair
// "age": 31,//pair
// ....
// "something else": {//whole thing is pair, but nested.
//    "deeper key": "deeper value"
//   }
// }

public class CompareJson {
    public class Json {
        HashMap<String, Entry> dict = new HashMap();

        @Override
        public String toString() {
            return "Json{" +
                       dict +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Json other = (Json) o;
            // Each of my key other have same value;
            for (String key : dict.keySet()) {
                if (!dict.get(key).equals(other.dict.get(key))) {
                    return false;
                }
            }
            // Each of other key, I have
            for (String key : other.dict.keySet()) {
                if (!other.dict.get(key).equals(dict.get(key))) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dict);
        }
    }

    public class Entry {
        String string;
        Json json;

        @Override
        public String toString() {
            if (string != null) {
                return string;
            } else if (json != null) {
                return json.toString();
            }
            return "[empty]";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry other = (Entry) o;
            if (string == null && other.string == null) {
                return json != null && json.equals(other.json);
            } else if (json == null && other.json == null) {
                return string != null && string.equals(other.string);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(string, json);
        }
    }

    public boolean compareJson(String lhs, String rhs) {
        Json lJson = parse(lhs);
        Json rJson = parse(rhs);
        return lJson.equals(rJson);
    }

    public Json parse(String str) {
        if (str.charAt(0) == '{') {
            return parse(str.substring(1, str.length() - 1));
        }
        Json json = new Json();
        StringBuilder token = new StringBuilder();
        Entry curEntry = new Entry();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
           if (c == ':') {
                json.dict.put(token.toString(), curEntry);
                token = new StringBuilder();
            } else if (c == ',') {
                if (token.length() != 0) {
                    curEntry.string = token.toString();
                }
                token = new StringBuilder();
                curEntry = new Entry();
            } else if (c == '{') {
                int counter = 1;
                int j = i + 1;
                while(counter > 0) {
                    if (str.charAt(j) == '{') {
                        counter++;
                    } else if (str.charAt(j) == '}') {
                        counter--;
                    }
                    j++;
                }
                curEntry.json = parse(str.substring(i + 1, j));
                i = j;
                curEntry = new Entry();
            } else if (c != '}' && c != ' ') {
                token.append(c);
            }
            i++;
        }
        if (token.length() != 0) {
            curEntry.string = token.toString();
        }
        return json;
    }

    public static void main(String[] args) {
        String test1 = "hello: me, hi: {yo: you, he: {1:1, 2: 2, 3: 3}, xi: xi}";
        String test2 = "hello: me, hi: {yo: you, he: {3:3, 2: 2, 1: 1, 7:7}, xi: xi}";
        CompareJson cj = new CompareJson();
        Json parsed1 = cj.parse(test1);
        Json parsed2 = cj.parse(test2);
        System.out.println(cj.compareJson(test1, test2));
    }
}
