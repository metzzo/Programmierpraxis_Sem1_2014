package at.pwd.asciishop.app.metrics;

import java.util.*;

/**
 * Created by Robert on 24.01.2015.
 */
public class MetricSet<E> extends LinkedHashSet<E> {
    public MetricSet<E> search(final E e, final Metric<? super E> m) {
        E minDist = null;
        for (E img : this) {
            if (minDist == null || m.distance(img, e) < m.distance(minDist, e)) {
                minDist = img;
            }
        }
        final MetricSet<E> newSet = new MetricSet<E>();
        newSet.add(minDist);

        return newSet;
    }
}
