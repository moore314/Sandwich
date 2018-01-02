package watchtower.ayalacashier;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Moore on 12/19/2017.
 */

public class Crypto {

    String timeStamp;
    int ZERO = 0, ONE = 1, TWO = 2, TRE = 3, FR = 4;
    int [] primes = {2,3,5,7,11,13,17,19,23,27,31,71,91,32,72};
    String [] words = {"toast","ebrad","ojell", "sushi","Haiti","ghips","sadla","zpiza","poste","aptsa",
                                "oudnt","iolve","bagal","ononi","emlon","pgrae","zucre","suypr","sceau","bacon",
                                "chil","hoey","oreos","orace","biasl","cidre","biere","poule","aioli","crepe",
                                "xmas", "stra","pomo","jrjr","onyx","alep","yule","sway","bano","tamoh",
                                "oglve","hoto","play","clcik","veuid","lcko","clcok","obok","setur","puptp",//5
                                "caef","lenz","jared","tewet","apnav","brgpd","tasty","pbay","dlish","xtyri",
                                "syekp","angr","soja","wihs","ksafc","wpdu","vkwnn","plsnk","rtsja","thbyk",
                                "jjosl","akuo","khnur","mayim","mijmy","adgj","qzwxe","rcwzq","pqlak","neksy",
                                "impal","baby","dean","sammy","bobby","njac","thov","rumv","acgv","ntbh",
                                "rufus","mily","mary","john","cass","lilly","vbily","jcak","jesrs","kurh",//10
                                "cind", "gaus","azzaz","timon","tejas","pero","cliro","rico","eins","kaot",
                                "sirri","ebzx","emam","rubks","fppv","carry","kuzko","pidra","guata","erhd",
                                "riniy","vdrhs","mloly","cudry","bdoby","coins","mrji","cloka","jsame","kknus",
                                "elder","dorac","wand","dkry","tkcux","xbhhp","stone","xbhmw","kuphi","chhch",
                                "ckte","rhnux","glass","nrusr","nubh","pspuy","prubd","yukg","poly","crmei",//15
                                "juice","dchg","phyr","brbhv","vtrh","rhskp","phkmw","xecrx","uhzkh","ruyc",
                                "prhbx","ajny","nfapv","vurx","tukh","ctry","thrhx","pomme","thrv","ebhuy",
                                "xusuy","hxnhi","trhtk","tkshi","xhncv","ptmwv","ehxr","llama","pxbyr","puvsc",
                                "nudkh","cdhrv","nscks","tree","dwush","nhkx","segar","pbxhc","njac","ehbur",//////////
                                "thby","stck","vhuo","long","timr","bones","sweet","child","tefi","diego",//20
                                "andy","candy","ross","eddie","aimee","mayor","ernie","barny","potus","rally",
                                "read","fufc","aksui","lbrs","xbtry","mick","nuryh","emmet","xeuk","juuv",
                                "tehi","leche","ehsx","fran","khty","xyhc","husv","trek","rshu","kusv",
                                "flor","call","arku","hbehz","shzbh","tryur","nubyv","ehauy","vtbx","tkxv",
                                "ntrx","ehhy","bheu","cielo","lilto","xsrhe","runtu","xubyv","hukhv","nynui",//25
                                "fhxt","xyto","tjuy","yhtdu","ptmwv","ehngy","ytmwu","rnhru","xypbu","btmwu",
                                "rama","tnnv","ehrv","jukhv","ebskv","eugen","mateo","luetx","barto","bucte",
                                "tvcv","ntre","thur","osric","euuhi","huvti","xhbsh","tkubv","pkhav","mwrkh",
                                "bgnh","chkh", "lurv","ehhyh","nhhkh","xerky","nurdi","xkunv","xhkch","thhkv",
                                "rcev","rguy","tchv","ahryk","hubhy","tchyk","ehhxh","tamin","tbsrv","vhhkh",//30
                                "noah","drwe","vgne","nubtk","mhcad","nrhcv","nkhcv","dwrnh","crsih","lneea",
                                "tusy","yunx","aprs","nhxui","ebeui","kuxhv","xuphv","shnui","ayhci","tkurv",
                                "arui","trei","phsh","iaezl","etlly","avcui","thykv","xhkcr","trhev","snubv",
                                "kuhx","etrk","zurk","kuthx","ftrkh","oiasn","idngo","grnta","phktr","dxyhi",
                                "eegd","jhhn","xhzr","tsrhi","xuzti","atbtk","nhhek","tnawy","mxypt","thrnv",//35
                                "khto","yunr","tbjk","dtaes","yjrke","xytnp","sueyr","erkux","xhxeu","ebshc",
                                "rnui","vbrh","ehnh","sbhtk","llena"};

    //final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
    //final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:HH");

    public Crypto()
    {
        Log.d("TKT_crypto","krypt was created=================================");
        /*
        for(int i = 0; i<words.length; i++)
        {
            if(words[i].length()!=4 && words[i].length() != 5)
            {
                Log.d("TKT_crypto","words["+i+"]: "+words[i]);
            }
        }
        */

    }

    /*
    timeStamp = new SimpleDateFormat("mm.HH.MM.dd").format(new Date());
        Log.d("TKT_crypto","timeStamp: "+timeStamp);
        Log.d("TKT_crypto","generateCrypro: "  + generateCrypto());
     */
    public String generateCrypto()
    {
        Log.d("TKT_crypto","generateCrypto=============");
        String krypto = "";
        timeStamp = new SimpleDateFormat("mm.HH.MM.dd").format(new Date());
        String [] stamp = timeStamp.split("\\.");
        int randInd = (int)(Math.random()*primes.length);
        int dayOfYear = Cashier.c.get(Calendar.DAY_OF_YEAR);
        Log.d("TKT_crypto","timeStamp1: "+timeStamp);
        Log.d("TKT_crypto","dayOYear: "+dayOfYear);
        Log.d("TKT_crypto","words.length: "+words.length);
        Log.d("TKT_crypto","words[]: "+words[dayOfYear-1]);
        Log.d("TKT_crypto","rand: "+randInd);
        Log.d("TKT_crypto","primes[]: "+primes[randInd]);
        Log.d("TKT_crypto","stamp.length: "+stamp.length);

        char [] l = words[dayOfYear-1].toCharArray();

        krypto = primes[randInd]+""+ l[0] + stamp[ZERO] + l[1] + stamp[ONE] + l[2] + stamp[TWO] + l[3] + stamp[TRE];
        if(l.length == 5)
            krypto += l[4];




        return krypto;
    }




}
