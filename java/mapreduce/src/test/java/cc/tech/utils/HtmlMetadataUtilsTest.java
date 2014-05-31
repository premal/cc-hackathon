package cc.tech.utils;

import org.json.JSONObject;
import org.junit.Test;

public class HtmlMetadataUtilsTest {
    String json = "{\n" +
            "    \"Envelope\": {\n" +
            "        \"Format\": \"WARC\",\n" +
            "        \"WARC-Header-Length\": \"559\",\n" +
            "        \"Block-Digest\": \"sha1:DWC7TB5ADFKD2CGHT3UFCCQHUXUHXWRW\",\n" +
            "        \"Actual-Content-Length\": \"58016\",\n" +
            "        \"WARC-Header-Metadata\": {\n" +
            "            \"WARC-Type\": \"response\",\n" +
            "            \"WARC-Date\": \"2013-06-20T12:35:34Z\",\n" +
            "            \"WARC-Warcinfo-ID\": \"<urn:uuid:97382ea3-e68b-4c60-b29a-1d3d2807dc96>\",\n" +
            "            \"Content-Length\": \"58016\",\n" +
            "            \"WARC-Record-ID\": \"<urn:uuid:38679379-82b1-42c8-a4ab-a1d9ff9629ab>\",\n" +
            "            \"WARC-Block-Digest\": \"sha1:DWC7TB5ADFKD2CGHT3UFCCQHUXUHXWRW\",\n" +
            "            \"WARC-Payload-Digest\": \"sha1:Y56H2S57XLUJWPXT2WHPV6NDHUXPRTJO\",\n" +
            "            \"WARC-Target-URI\": \"http://01varvara.wordpress.com/tag/valentin-serov/\",\n" +
            "            \"WARC-IP-Address\": \"76.74.254.123\",\n" +
            "            \"WARC-Concurrent-To\": \"<urn:uuid:da315aa0-3da5-4b76-b420-e0b5293d8b7a>\",\n" +
            "            \"Content-Type\": \"application/http; msgtype=response\"\n" +
            "        },\n" +
            "        \"Payload-Metadata\": {\n" +
            "            \"Trailing-Slop-Length\": \"4\",\n" +
            "            \"Actual-Content-Type\": \"application/http; msgtype=response\",\n" +
            "            \"HTTP-Response-Metadata\": {\n" +
            "                \"Headers\": {\n" +
            "                    \"X-Pingback\": \"http://01varvara.wordpress.com/xmlrpc.php\",\n" +
            "                    \"Vary\": \"Cookie\",\n" +
            "                    \"Date\": \"Thu, 20 Jun 2013 12:35:33 GMT\",\n" +
            "                    \"X-hacker\": \"If you're reading this, you should visit automattic.com/jobs and apply to join the fun, mention this header.\",\n" +
            "                    \"Content-Type\": \"text/html; charset=UTF-8\",\n" +
            "                    \"Connection\": \"close\",\n" +
            "                    \"Server\": \"nginx\"\n" +
            "                },\n" +
            "                \"Headers-Length\": \"319\",\n" +
            "                \"Entity-Length\": \"57697\",\n" +
            "                \"Entity-Trailing-Slop-Bytes\": \"0\",\n" +
            "                \"Response-Message\": {\n" +
            "                    \"Status\": \"200\",\n" +
            "                    \"Version\": \"HTTP/1.0\",\n" +
            "                    \"Reason\": \"OK\"\n" +
            "                },\n" +
            "                \"HTML-Metadata\": {\n" +
            "                    \"Links\": [\n" +
            "                        {\n" +
            "                            \"path\": \"STYLE/#text\",\n" +
            "                            \"href\": \"http://s1.wp.com/wp-content/themes/pub/rubric/style.css?m=1368529391g\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"STYLE/#text\",\n" +
            "                            \"href\": \"http://01varvara.files.wordpress.com/2010/06/cropped-dmitri-belyukin-white-russia-in-exile-1992-19941.jpg\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Art and Faith\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov. A Girl with Peaches.&nbsp;1887\",\n" +
            "                            \"title\": \"Permalink to Valentin Serov. A Girl with Peaches.&nbsp;1887\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2009/12/11/valentin-serov-a-girl-with-peaches-1887/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"19th century\",\n" +
            "                            \"title\": \"View all posts in 19th century\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/19th-century/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"domestic\",\n" +
            "                            \"title\": \"View all posts in domestic\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/domestic/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fine art\",\n" +
            "                            \"title\": \"View all posts in fine art\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/fine-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"human study\",\n" +
            "                            \"title\": \"View all posts in human study\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/human-study/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portrait\",\n" +
            "                            \"title\": \"View all posts in portrait\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/portrait/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"View all posts in Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fruit\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/fruit/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"girl\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/girl/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Imperial Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/imperial-russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Impressionism\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/impressionism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"manor house\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/manor-house/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"title\": \"Valentin Serov-Girl with Peaches 1887\",\n" +
            "                            \"alt\": \"\",\n" +
            "                            \"path\": \"IMG@/src\",\n" +
            "                            \"url\": \"http://01varvara.files.wordpress.com/2009/12/valentin-serov-girl-with-peaches-18871.jpg?w=1200&#038;h=1360\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2009/12/11/valentin-serov-a-girl-with-peaches-1887/valentin-serov-girl-with-peaches-1887/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"title\": \"Valentin Serov\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Valentin_Serov\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian art\",\n" +
            "                            \"title\": \"Russian culture\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Russian_culture\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A World of Lost Brightness, Part Three (music by&nbsp;Chopin)\",\n" +
            "                            \"title\": \"Permalink to A World of Lost Brightness, Part Three (music by&nbsp;Chopin)\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2007/11/23/a-world-of-lost-brightness-part-three-music-by-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"art music\",\n" +
            "                            \"title\": \"View all posts in art music\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/art-music/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"early modern\",\n" +
            "                            \"title\": \"View all posts in early modern\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/early-modern/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fine art\",\n" +
            "                            \"title\": \"View all posts in fine art\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/fine-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"human study\",\n" +
            "                            \"title\": \"View all posts in human study\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/human-study/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portrait\",\n" +
            "                            \"title\": \"View all posts in portrait\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/portrait/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"View all posts in Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Chopin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/frederic-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"History\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Imperial Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/imperial-russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nocturne\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/nocturne/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian history\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"IFRAME@/src\",\n" +
            "                            \"url\": \"http://www.youtube.com/embed/9xLWBegSIew?version=3&#038;rel=1&#038;fs=1&#038;showsearch=0&#038;showinfo=1&#038;iv_load_policy=1&#038;wmode=transparent\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"title\": \"Valentin Serov\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Valentin_Serov\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portraitist\",\n" +
            "                            \"title\": \"Portrait\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Portrait\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"heart attack\",\n" +
            "                            \"title\": \"Myocardial infarction\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Myocardial_infarction\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nocturne in C sharp minor\",\n" +
            "                            \"title\": \"Nocturne in C-sharp minor, Op. posth. (Chopin)\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Nocturne_in_C-sharp_minor%2C_Op._posth._%28Chopin%29\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"title\": \"Frédéric Chopin\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Fr%C3%A9d%C3%A9ric_Chopin\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Chopin nocturnes\",\n" +
            "                            \"title\": \"Nocturnes (Chopin)\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Nocturnes_%28Chopin%29\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A World of Lost Brightness, Part Two (music by&nbsp;Chopin)\",\n" +
            "                            \"title\": \"Permalink to A World of Lost Brightness, Part Two (music by&nbsp;Chopin)\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2007/11/23/a-world-of-lost-brightness-part-two-music-by-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"art music\",\n" +
            "                            \"title\": \"View all posts in art music\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/art-music/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"early modern\",\n" +
            "                            \"title\": \"View all posts in early modern\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/early-modern/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fine art\",\n" +
            "                            \"title\": \"View all posts in fine art\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/fine-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portrait\",\n" +
            "                            \"title\": \"View all posts in portrait\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/portrait/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"View all posts in Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/frederic-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"History\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Imperial Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/imperial-russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"New Martyr\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/new-martyr/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nocturne\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/nocturne/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian culture\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-culture/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian history\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Silver Age\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/silver-age/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Tsar\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/tsar/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Yekaterinburg\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/yekaterinburg/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"IFRAME@/src\",\n" +
            "                            \"url\": \"http://www.youtube.com/embed/ZAlEi1ZJ5GI?version=3&#038;rel=1&#038;fs=1&#038;showsearch=0&#038;showinfo=1&#038;iv_load_policy=1&#038;wmode=transparent\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"title\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Valentin_Serov\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Tsar\",\n" +
            "                            \"title\": \"Tsar\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Tsar\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nikolai Aleksandrovich\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Nicholas_II_of_Russia\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Grand Princess\",\n" +
            "                            \"title\": \"Grand Princess\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Grand_Princess\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Olga Nikolaevna\",\n" +
            "                            \"title\": \"Grand Duchess Olga Nikolaevna of Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Grand_Duchess_Olga_Nikolaevna_of_Russia\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Yekaterinburg\",\n" +
            "                            \"title\": \"Yekaterinburg\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Yekaterinburg\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"canonisation\",\n" +
            "                            \"title\": \"Canonization\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Canonization\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"New Martyrs\",\n" +
            "                            \"title\": \"New Martyr\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/New_Martyr\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nocturne\",\n" +
            "                            \"title\": \"Nocturne\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Nocturne\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"D flat major\",\n" +
            "                            \"title\": \"D-flat major\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/D-flat_major\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"title\": \"Frédéric Chopin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Fr%C3%A9d%C3%A9ric_Chopin\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Yevgeny Kissin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Kissin\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A World of Lost Brightness, Part One (music by&nbsp;Chopin)\",\n" +
            "                            \"title\": \"Permalink to A World of Lost Brightness, Part One (music by&nbsp;Chopin)\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2007/11/23/a-world-of-lost-brightness-part-one-music-by-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"art music\",\n" +
            "                            \"title\": \"View all posts in art music\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/art-music/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"early modern\",\n" +
            "                            \"title\": \"View all posts in early modern\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/early-modern/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fine art\",\n" +
            "                            \"title\": \"View all posts in fine art\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/fine-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portrait\",\n" +
            "                            \"title\": \"View all posts in portrait\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/portrait/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"View all posts in Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A-flat major\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/a-flat-major/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Bolshevik\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/bolshevik/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"France\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/france/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/frederic-chopin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"History\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Imperial Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/imperial-russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Nocturne\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/nocturne/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Portrait\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/portrait-2/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portraiture\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/portraiture/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian history\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"United States\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/united-states/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Waltz\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/waltz/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"IFRAME@/src\",\n" +
            "                            \"url\": \"http://www.youtube.com/embed/tzQs3izxIQ8?version=3&#038;rel=1&#038;fs=1&#038;showsearch=0&#038;showinfo=1&#038;iv_load_policy=1&#038;wmode=transparent\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"title\": \"Valentin Serov\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Valentin_Serov\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Impressionists\",\n" +
            "                            \"title\": \"Impressionism\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Impressionism\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portraitist\",\n" +
            "                            \"title\": \"Portrait\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Portrait\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Petersburg\",\n" +
            "                            \"title\": \"Saint Petersburg\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Saint_Petersburg\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Bolshevik\",\n" +
            "                            \"title\": \"Bolshevik\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Bolshevik\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"France\",\n" +
            "                            \"title\": \"France\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/France\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Orthodox Church\",\n" +
            "                            \"title\": \"Eastern Orthodox Church\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Eastern_Orthodox_Church\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"USA\",\n" +
            "                            \"title\": \"United States\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/United_States\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A flat major\",\n" +
            "                            \"title\": \"A-flat major\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/A-flat_major\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Frédéric Chopin\",\n" +
            "                            \"title\": \"Frédéric Chopin\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Fr%C3%A9d%C3%A9ric_Chopin\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valery Badakva. A Walk in the Winter.&nbsp;2002\",\n" +
            "                            \"title\": \"Permalink to Valery Badakva. A Walk in the Winter.&nbsp;2002\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2007/11/14/a-walk-in-the-winter/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"contemporary\",\n" +
            "                            \"title\": \"View all posts in contemporary\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/contemporary/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"fine art\",\n" +
            "                            \"title\": \"View all posts in fine art\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/fine-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"View all posts in Russian\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"urban scene\",\n" +
            "                            \"title\": \"View all posts in urban scene\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/category/urban-scene/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Impressionism\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/impressionism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Neo-Impressionism\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/neo-impressionism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"snow\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/snow/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Soviet Union\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/soviet-union/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"town scene\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/town-scene/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valery Badakva\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/valery-badakva/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"winter\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/winter/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"alt\": \"\",\n" +
            "                            \"path\": \"IMG@/src\",\n" +
            "                            \"url\": \"http://01varvara.files.wordpress.com/2008/02/valery-badakva-a-walk-in-the-winter-2002-e1268553298508.jpg?w=700&#038;h=618\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2007/11/14/a-walk-in-the-winter/attachment/692/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Impressionism\",\n" +
            "                            \"title\": \"Impressionism\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Impressionism\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Soviet\",\n" +
            "                            \"title\": \"Soviet Union\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Soviet_Union\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Valentin Serov\",\n" +
            "                            \"title\": \"Valentin Serov\",\n" +
            "                            \"target\": \"_blank\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://en.wikipedia.org/wiki/Valentin_Serov\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"&laquo; Mar\",\n" +
            "                            \"title\": \"View posts for March 2013\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/2013/03/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"FORM@/action\",\n" +
            "                            \"method\": \"get\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"A Daily Column on Painting by Lisa Towers and Bill Jones&#8230; very good site maintained by two wor\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://onpainting.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Art and Faith, Too: My other art site (started 06/10)\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://03varvara.wordpress.com/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Art in Faith&#8230; with a focus narrowly on iconography per se&#8230;\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://05varvara.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Voices from Russia, Too: a more image-driven site\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://04varvara.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Voices from Russia: News and much more from Russia and other things with a Russian perspective\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://02varvara.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"FORM@/action\",\n" +
            "                            \"method\": \"post\",\n" +
            "                            \"url\": \"https://subscribe.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"alt\": \"wordpress com stats\",\n" +
            "                            \"path\": \"IMG@/src\",\n" +
            "                            \"url\": \"http://c.statcounter.com/7320784/0/58ab83e2/0/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"title\": \"wordpress com stats\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://statcounter.com/wordpress.org/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Allegory\",\n" +
            "                            \"title\": \"24 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/allegory-2/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Apollinary Vasnetsov\",\n" +
            "                            \"title\": \"26 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/apollinary-vasnetsov/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"avante garde\",\n" +
            "                            \"title\": \"28 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/avante-garde/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Boris Kustodiev\",\n" +
            "                            \"title\": \"26 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/boris-kustodiev/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Christ\",\n" +
            "                            \"title\": \"35 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/christ/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Christian art\",\n" +
            "                            \"title\": \"101 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/christian-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Christianity\",\n" +
            "                            \"title\": \"100 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/christianity/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"churches\",\n" +
            "                            \"title\": \"27 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/churches/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"countryside\",\n" +
            "                            \"title\": \"44 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/countryside/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Eastern Orthodox Church\",\n" +
            "                            \"title\": \"85 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/eastern-orthodox-church/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Expressionism\",\n" +
            "                            \"title\": \"27 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/expressionism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"French\",\n" +
            "                            \"title\": \"21 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/french/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Great Patriotic War\",\n" +
            "                            \"title\": \"25 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/great-patriotic-war/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"History\",\n" +
            "                            \"title\": \"89 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Igor Grabar\",\n" +
            "                            \"title\": \"49 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/igor-grabar/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Imperial Russia\",\n" +
            "                            \"title\": \"77 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/imperial-russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Jesus\",\n" +
            "                            \"title\": \"32 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/jesus/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Jewish\",\n" +
            "                            \"title\": \"30 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/jewish/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Jewish art\",\n" +
            "                            \"title\": \"28 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/jewish-art/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Kuzma Petrov-Vodkin\",\n" +
            "                            \"title\": \"33 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/kuzma-petrov-vodkin/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Marc Chagall\",\n" +
            "                            \"title\": \"27 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/marc-chagall/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Moscow\",\n" +
            "                            \"title\": \"33 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/moscow/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Neo-Impressionism\",\n" +
            "                            \"title\": \"55 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/neo-impressionism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Orthodox\",\n" +
            "                            \"title\": \"34 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/orthodox/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Painting\",\n" +
            "                            \"title\": \"368 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/painting/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"patriotic\",\n" +
            "                            \"title\": \"46 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/patriotic/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Peredvizhniki\",\n" +
            "                            \"title\": \"22 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/peredvizhniki/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Portrait\",\n" +
            "                            \"title\": \"53 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/portrait-2/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"portraiture\",\n" +
            "                            \"title\": \"68 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/portraiture/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Red Army\",\n" +
            "                            \"title\": \"33 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/red-army/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Religion and Spirituality\",\n" +
            "                            \"title\": \"115 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/religion-and-spirituality/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russia\",\n" +
            "                            \"title\": \"324 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russia/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian\",\n" +
            "                            \"title\": \"178 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian history\",\n" +
            "                            \"title\": \"95 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-history/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Russian Orthodox Church\",\n" +
            "                            \"title\": \"49 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/russian-orthodox-church/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"snow\",\n" +
            "                            \"title\": \"27 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/snow/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"soldiers\",\n" +
            "                            \"title\": \"28 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/soldiers/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Soviet Union\",\n" +
            "                            \"title\": \"99 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/soviet-union/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"summer\",\n" +
            "                            \"title\": \"45 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/summer/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Surrealism\",\n" +
            "                            \"title\": \"27 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/surrealism/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"trees\",\n" +
            "                            \"title\": \"44 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/trees/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"USSR\",\n" +
            "                            \"title\": \"85 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/ussr/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"winter\",\n" +
            "                            \"title\": \"39 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/winter/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"woman\",\n" +
            "                            \"title\": \"42 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/woman/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"World War II\",\n" +
            "                            \"title\": \"36 topics\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://01varvara.wordpress.com/tag/world-war-ii/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Rubric\",\n" +
            "                            \"title\": \"Learn more about this theme\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://theme.wordpress.com/themes/rubric/\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Blog at WordPress.com\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://wordpress.com/?ref=footer\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"alt\": \"\",\n" +
            "                            \"path\": \"IMG@/src\",\n" +
            "                            \"url\": \"//pixel.quantserve.com/pixel/p-18-mFEk4J448M.gif?labels=%2Clanguage.en%2Ctype.wpcom\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Follow\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"javascript:void(0)\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"path\": \"FORM@/action\",\n" +
            "                            \"method\": \"post\",\n" +
            "                            \"url\": \"https://subscribe.wordpress.com\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"text\": \"Powered by WordPress.com\",\n" +
            "                            \"path\": \"A@/href\",\n" +
            "                            \"url\": \"http://wordpress.com/signup/?ref=lof\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"alt\": \"\",\n" +
            "                            \"path\": \"IMG@/src\",\n" +
            "                            \"url\": \"http://stats.wordpress.com/b.gif?v=noscript\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"Head\": {\n" +
            "                        \"Link\": [\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"pingback\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/xmlrpc.php\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"alternate\",\n" +
            "                                \"type\": \"application/rss+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/feed/\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"alternate\",\n" +
            "                                \"type\": \"application/rss+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/comments/feed/\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"alternate\",\n" +
            "                                \"type\": \"application/rss+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/tag/valentin-serov/feed/\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"stylesheet\",\n" +
            "                                \"type\": \"text/css\",\n" +
            "                                \"url\": \"http://s2.wp.com/_static/??-eJyNjsEKwjAQRH/IuIo10IP4LWm7btJusqHZEvx7qyAWevE0DLzHDNRsekmKSSEuJvNCIRXgMGGBETW7fjKfduxLOcAG71joJwgRDrKoeQizVKhhINSdtNnIUlaaXZih6JPxX9a7OST65s5Sj3H97hsgls7xG7jH2/libXu6to0dX0seWn8=\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"EditURI\",\n" +
            "                                \"type\": \"application/rsd+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/xmlrpc.php?rsd\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"wlwmanifest\",\n" +
            "                                \"type\": \"application/wlwmanifest+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/wp-includes/wlwmanifest.xml\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"shortcut icon\",\n" +
            "                                \"type\": \"image/x-icon\",\n" +
            "                                \"url\": \"http://0.gravatar.com/blavatar/48eae90ee0d7528a7357140a6074960b?s=16\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"icon\",\n" +
            "                                \"type\": \"image/x-icon\",\n" +
            "                                \"url\": \"http://0.gravatar.com/blavatar/48eae90ee0d7528a7357140a6074960b?s=16\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"apple-touch-icon-precomposed\",\n" +
            "                                \"url\": \"http://0.gravatar.com/blavatar/e60796a457e70f1bf884ca3a59af96a9?s=114\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"openid.server\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/?openidserver=1\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"openid.delegate\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"search\",\n" +
            "                                \"type\": \"application/opensearchdescription+xml\",\n" +
            "                                \"url\": \"http://01varvara.wordpress.com/osd.xml\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"LINK@/href\",\n" +
            "                                \"rel\": \"search\",\n" +
            "                                \"type\": \"application/opensearchdescription+xml\",\n" +
            "                                \"url\": \"http://wordpress.com/opensearch.xml\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"Scripts\": [\n" +
            "                            {\n" +
            "                                \"path\": \"SCRIPT@/src\",\n" +
            "                                \"type\": \"text/javascript\",\n" +
            "                                \"url\": \"http://s2.wp.com/_static/??-eJyFjdEKwjAMRX/I2g3RPYnfsq1ZSW2b2qQW/XorKCgKe0pIzr1H16RmigJRtGMdaEIPqjDk0babwrjQ1vFGNw7j7IsBfoLuUiDfXmMVUAFtHgXe4IcxEUsA5qb78/3WYLwi1FXMgaRxPqsMjPef1smTVckXi5F12y0YKqIW8p6qrmgsSMucwrHfDX03dPvu4B6HJW45\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"SCRIPT@/src\",\n" +
            "                                \"type\": \"text/javascript\",\n" +
            "                                \"url\": \"//use.typekit.net/jtk8ghq.js\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"SCRIPT@/src\",\n" +
            "                                \"type\": \"text/javascript\",\n" +
            "                                \"url\": \"http://s1.wp.com/wp-content/js/devicepx.js?m=1368969037g\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"SCRIPT@/src\",\n" +
            "                                \"type\": \"text/javascript\",\n" +
            "                                \"url\": \"http://mash.network.coull.com/getaffiliatorjs?pid=8165&#038;website_ref=2061054&#038;ver=3.6-beta3-24432\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"path\": \"SCRIPT@/src\",\n" +
            "                                \"type\": \"text/javascript\",\n" +
            "                                \"url\": \"http://s.stats.wordpress.com/w.js?21\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"Metas\": [\n" +
            "                            {\n" +
            "                                \"content\": \"text/html; charset=UTF-8\",\n" +
            "                                \"http-equiv\": \"Content-Type\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"WordPress.com\",\n" +
            "                                \"name\": \"generator\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"Art and Faith\",\n" +
            "                                \"name\": \"application-name\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"width=device-width;height=device-height\",\n" +
            "                                \"name\": \"msapplication-window\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"One woman&#039;s understanding of the linkage between culture and belief\",\n" +
            "                                \"name\": \"msapplication-tooltip\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"name=Subscribe;action-uri=http://01varvara.wordpress.com/feed/;icon-uri=http://0.gravatar.com/blavatar/48eae90ee0d7528a7357140a6074960b?s=16\",\n" +
            "                                \"name\": \"msapplication-task\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"name=Sign up for a free blog;action-uri=http://wordpress.com/signup/;icon-uri=http://s2.wp.com/i/favicon.ico\",\n" +
            "                                \"name\": \"msapplication-task\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"name=WordPress.com Support;action-uri=http://support.wordpress.com/;icon-uri=http://s2.wp.com/i/favicon.ico\",\n" +
            "                                \"name\": \"msapplication-task\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"name=WordPress.com Forums;action-uri=http://forums.wordpress.com/;icon-uri=http://s2.wp.com/i/favicon.ico\",\n" +
            "                                \"name\": \"msapplication-task\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"Posts about Valentin Serov on Art and Faith\",\n" +
            "                                \"name\": \"title\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"content\": \"Posts about Valentin Serov written by 01varvara\",\n" +
            "                                \"name\": \"description\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"Title\": \"Valentin Serov | Art and Faith\"\n" +
            "                    }\n" +
            "                },\n" +
            "                \"Entity-Digest\": \"sha1:Y56H2S57XLUJWPXT2WHPV6NDHUXPRTJO\"\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"Container\": {\n" +
            "        \"Compressed\": true,\n" +
            "        \"Gzip-Metadata\": {\n" +
            "            \"Footer-Length\": \"8\",\n" +
            "            \"Deflate-Length\": \"13723\",\n" +
            "            \"Header-Length\": \"10\",\n" +
            "            \"Inflated-CRC\": \"864288505\",\n" +
            "            \"Inflated-Length\": \"58579\"\n" +
            "        },\n" +
            "        \"Offset\": \"25206\",\n" +
            "        \"Filename\": \"CC-MAIN-20130516134005-00099-ip-10-60-113-184.ec2.internal.warc.gz\"\n" +
            "    }\n" +
            "}";
    JSONObject jsonObject = new JSONObject(json);

    @Test
    public void testGetAllScriptTagURLDomain() throws Exception {
        System.out.println(HtmlMetadataUtils.getURLDomainFromScriptTags(jsonObject));
    }

    @Test
    public void testGetPageDomain() throws Exception {
        System.out.println(HtmlMetadataUtils.getPageDomain(jsonObject));
    }
}
