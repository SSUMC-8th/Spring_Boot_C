package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStorePhoto is a Querydsl query type for StorePhoto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStorePhoto extends EntityPathBase<StorePhoto> {

    private static final long serialVersionUID = -51735683L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStorePhoto storePhoto = new QStorePhoto("storePhoto");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath photo_url = createString("photo_url");

    public final QStore store;

    public QStorePhoto(String variable) {
        this(StorePhoto.class, forVariable(variable), INITS);
    }

    public QStorePhoto(Path<? extends StorePhoto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStorePhoto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStorePhoto(PathMetadata metadata, PathInits inits) {
        this(StorePhoto.class, metadata, inits);
    }

    public QStorePhoto(Class<? extends StorePhoto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

