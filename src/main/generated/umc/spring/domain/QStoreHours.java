package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreHours is a Querydsl query type for StoreHours
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreHours extends EntityPathBase<StoreHours> {

    private static final long serialVersionUID = -58909606L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreHours storeHours = new QStoreHours("storeHours");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<umc.spring.domain.enums.DayOfWeek> day = createEnum("day", umc.spring.domain.enums.DayOfWeek.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStoreHours(String variable) {
        this(StoreHours.class, forVariable(variable), INITS);
    }

    public QStoreHours(Path<? extends StoreHours> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreHours(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreHours(PathMetadata metadata, PathInits inits) {
        this(StoreHours.class, metadata, inits);
    }

    public QStoreHours(Class<? extends StoreHours> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

