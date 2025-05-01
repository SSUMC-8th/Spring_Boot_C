package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreImg is a Querydsl query type for StoreImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreImg extends EntityPathBase<StoreImg> {

    private static final long serialVersionUID = 1751892942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreImg storeImg = new QStoreImg("storeImg");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public QStoreImg(String variable) {
        this(StoreImg.class, forVariable(variable), INITS);
    }

    public QStoreImg(Path<? extends StoreImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreImg(PathMetadata metadata, PathInits inits) {
        this(StoreImg.class, metadata, inits);
    }

    public QStoreImg(Class<? extends StoreImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

