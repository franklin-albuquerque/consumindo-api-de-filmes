import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITENS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    List<Map<String, String>> parse(String json) {
        Matcher matcher = REGEX_ITENS.matcher(json);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Não encontrou itens.");
        }

        String[] itens = matcher.group(1).split("\\},\\{");
        List<Map<String, String>> dados = new ArrayList<>();

        for(String item : itens) {
            Map<String, String> atributosDoItem = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while(matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                atributosDoItem.put(atributo, valor);
            }

            dados.add(atributosDoItem);
        }
        return dados;
    }
}
