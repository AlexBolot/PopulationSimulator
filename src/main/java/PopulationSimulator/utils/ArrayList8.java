package PopulationSimulator.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ArrayList8 class was coded by : Alexandre BOLOT
 .
 . Last modified : 30/01/18 01:18
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ("UnusedReturnValue")
public class ArrayList8<E> extends ArrayList<E>
{
    //region --------------- Attributes ----------------------
    private Random random = new Random();
    //endregion

    //region --------------- Constructors --------------------
    public ArrayList8 ()
    {
        super();
    }

    public ArrayList8 (@NotNull Collection<? extends E> c)
    {
        super(c);
    }

    public ArrayList8 (@NotNull E[] array) {super(Arrays.asList(array));}
    //endregion

    //region --------------- Methods -------------------------
    public E getRandom ()
    {
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");

        return get(random.nextInt(this.size()));
    }

    public E removeRandom ()
    {
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");

        return remove(random.nextInt(this.size()));
    }

    @NotNull
    public ArrayList8<E> merge (@NotNull Collection<? extends E> collection)
    {
        this.addAll(collection);
        return this;
    }

    public boolean addIf (@NotNull E value, @NotNull Predicate<? super E> filter)
    {
        return filter.test(value) && add(value);
    }

    public int addAllIf (@NotNull Collection<? extends E> collection, @NotNull Predicate<? super E> filter)
    {
        ArrayList8<E> paramList = new ArrayList8<>();
        paramList.addAll(collection);

        ArrayList8<E> sublist = paramList.subList(filter);

        this.addAll(sublist);

        return sublist.size();
    }

    @Contract (pure = true)
    public boolean contains (@NotNull Predicate<? super E> filter)
    {
        return this.stream().anyMatch(filter::test);
    }

    @Contract (pure = true)
    @SafeVarargs
    public final boolean contains (@NotNull E... items)
    {
        return Arrays.stream(items).anyMatch(this::contains);
    }

    public int countIf (@NotNull Predicate<? super E> filter)
    {
        return (int) this.stream().filter(filter::test).count();
    }

    @NotNull
    public ArrayList8<E> subList (@NotNull Predicate<? super E> filter)
    {
        ArrayList8<E> res = new ArrayList8<>();

        this.forEach(e -> res.addIf(e, filter));

        return res;
    }

    public Optional<E> findAny (@NotNull Predicate<? super E> filter)
    {
        return this.isEmpty() ? Optional.empty() : subList(filter).stream().findAny();
    }

    public Optional<E> findFirst (@NotNull Predicate<? super E> filter)
    {
        for (E e : this)
        {
            if (filter.test(e)) return Optional.of(e);
        }

        return Optional.empty();
    }

    public Optional<E> max (@NotNull Comparator<? super E> comparator)
    {
        if (this.isEmpty()) return Optional.empty();

        E max = this.getRandom();

        for (E e : this)
        {
            if (comparator.compare(e, max) > 0) max = e;
        }

        return Optional.of(max);
    }

    public Optional<E> min (@NotNull Comparator<? super E> comparator)
    {
        if (this.isEmpty()) return Optional.empty();

        E min = this.get(0);

        for (E e : this)
        {
            if (comparator.compare(e, min) < 0) min = e;
        }

        return Optional.of(min);
    }

    public Optional<E> reduce (@NotNull BinaryOperator<E> accumulator)
    {
        return this.stream().reduce(accumulator);
    }

    public <R> Stream<R> map (@NotNull Function<? super E, ? extends R> mapper)
    {
        return this.stream().map(mapper);
    }
    //endregion
}
