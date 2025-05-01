package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreHour is a Querydsl query type for StoreHour
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreHour extends EntityPathBase<StoreHour> {

    private static final long serialVersionUID = -1525920967L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreHour storeHour = new QStoreHour("storeHour");

    public final DateTimePath<java.time.LocalDateTime> close_time = createDateTime("close_time", java.time.LocalDateTime.class);

    public final EnumPath<java.time.DayOfWeek> day = createEnum("day", java.time.DayOfWeek.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> open_time = createDateTime("open_time", java.time.LocalDateTime.class);

    public final QStore store;

    public QStoreHour(String variable) {
        this(StoreHour.class, forVariable(variable), INITS);
    }

    public QStoreHour(Path<? extends StoreHour> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreHour(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreHour(PathMetadata metadata, PathInits inits) {
        this(StoreHour.class, metadata, inits);
    }

    public QStoreHour(Class<? extends StoreHour> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

