package home.kornhill;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class GuavaTest {

    @Test
    public void say_hello() {

        List<String> list = Lists.<String>newArrayList("Henry", "Agnes", "Anson", "Audrey");
        Collection<String> collection = Collections2.filter(list, Predicates.containsPattern("A"));
        System.out.println(list);
        System.out.println(collection);
    }

    @Test
    public void start_with() {
        ImmutableList list = ImmutableList.of("Henry", "Agnes", "Anson", "Audrey");
        Collection<String> collection = Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("An") || input.startsWith("Au");
            }

        });
        System.out.println(list);
        System.out.println(collection);
    }

    @Test
    public void get_empty_list_after_filter() {
        ImmutableList list = ImmutableList.of("Henry", "Agnes", "Anson", "Audrey");
        Collection<String> collection = Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("zz") || input.startsWith("xx");
            }

        });
        System.out.println(list);
        System.out.println(collection);
    }

    @Test
    public void transform_the_list() {

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("meta.");
            }
        };

        Function<String, String> func = new Function<String,String>(){
            @Override
            public String apply(String input) {
                return input.replaceFirst("meta.","");
            }
        };


        ImmutableList list = ImmutableList.of("meta.Henry", "meta.B", "meta.C", "meta.D","abc");
        Collection<String> collection = Collections2.filter(list,predicate);
        Collection<String> collection2 = Collections2.transform(Collections2.filter(list,predicate),func);

        System.out.println(list);
        System.out.println(collection);
        System.out.println(collection2);
    }

    @Test
    public void fluentIterables_test() {

        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("meta.");
            }
        };

        Function<String, String> func = new Function<String,String>(){
            @Override
            public String apply(String input) {
                return input.replaceFirst("meta.","");
            }
        };


        ImmutableList list = ImmutableList.of("meta.Henry", "meta.B", "meta.C", "meta.D","abc");
        Collection<String> collection = Collections2.filter(list,predicate);
        Collection<String> collection2 = Collections2.transform(Collections2.filter(list,predicate),func);

        List<String> newList= FluentIterable.from(list).filter(predicate).transform(func).toList();
        System.out.println(list);
        System.out.println(collection);
        System.out.println(collection2);
        System.out.println(newList);
    }

}
