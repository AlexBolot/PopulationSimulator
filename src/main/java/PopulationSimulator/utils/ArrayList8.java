package PopulationSimulator.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ArrayList8 class was coded by : Alexandre BOLOT
 .
 . Last modified : 20/01/18 00:11
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings ({"UnusedReturnValue", "ConstantConditions"})
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

    public ArrayList8 (Collection<? extends E> c)
    {
        super(c);
    }
    //endregion

    //region --------------- Methods -------------------------
    public E getRandom ()
    {
        //region --> Check params
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");
        //endregion

        return get(random.nextInt(this.size()));
    }

    public E removeRandom ()
    {
        //region --> Check params
        if (this.isEmpty()) throw new IndexOutOfBoundsException("List is empty");
        //endregion

        return remove(random.nextInt(this.size()));
    }

    public boolean addIf (@Nullable E value, @NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        return filter.test(value) && add(value);
    }

    public int addAllIf (@Nullable Collection<? extends E> collection, @Nullable Predicate<? super E> filter)
    {
        //region --> Check params
        if (collection == null) throw new IllegalArgumentException("Collection param is null");
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        ArrayList8<E> paramList = new ArrayList8<>();
        paramList.addAll(collection);

        ArrayList8<E> sublist = paramList.subList(filter);

        this.addAll(sublist);

        return sublist.size();
    }

    @Contract (pure = true)
    public boolean contains (@NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        return this.stream().anyMatch(filter::test);
    }

    public int countIf (@NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        return (int) this.stream().filter(filter::test).count();
    }

    public ArrayList8<E> subList (@NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        return new ArrayList8<E>()
        {{
            this.forEach(e -> addIf(e, filter));
        }};
    }

    public Optional<E> findAny (@NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        return this.isEmpty() ? Optional.empty() : subList(filter).stream().findAny();
    }

    public Optional<E> findFirst (@NotNull Predicate<? super E> filter)
    {
        //region --> Check params
        if (filter == null) throw new IllegalArgumentException("Filter param is null");
        //endregion

        for (E e : this)
        {
            if (filter.test(e)) return Optional.of(e);
        }

        return Optional.empty();
    }

    public Optional<E> max (@NotNull Comparator<? super E> comparator)
    {
        //region --> Check params
        if (comparator == null) throw new IllegalArgumentException("Comparator param is null");
        //endregion

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
        //region --> Check params
        if (comparator == null) throw new IllegalArgumentException("Comparator param is null");
        //endregion

        if (this.isEmpty()) return Optional.empty();

        E min = this.get(0);

        for (E e : this)
        {
            if (comparator.compare(e, min) < 0) min = e;
        }

        return Optional.of(min);
    }
    //endregion
}
