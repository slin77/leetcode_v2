package databrick;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

interface HtmlParser {
    public List<String> getUrls(String url);
}

public class MultThreadCrawler {
    private static Set<String> visited = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostName = getHostName(startUrl);
        crawl(hostName, htmlParser, startUrl);
        return new ArrayList<>(visited);
    }

    public void crawl(String hostName, HtmlParser htmlParser, String url) {
        List<Thread> threads = new ArrayList<>();
        for (String nextUrl : htmlParser.getUrls(url)) {
            if (getHostName(nextUrl).equals(hostName) && !visited.contains(nextUrl)) {
                visited.add(nextUrl);
                Thread t = new Thread(() -> crawl(hostName, htmlParser, nextUrl));
                threads.add(t);
                t.run();
            }
        }
        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getHostName(String url) {
        url = url.substring(7);
        int idx = url.indexOf("/");
        return idx == -1 ? "" : url.substring(0, idx);
    }
}
