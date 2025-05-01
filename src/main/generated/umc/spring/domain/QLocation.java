package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLocation is a Querydsl query type for Location
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocation extends EntityPathBase<Location> {

    private static final long serialVersionUID = 1935799393L;

    public static final QLocation location = new QLocation("location");

    public final StringPath address = createString("address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Store, QStore> storeList = this.<Store, QStore>createList("storeList", Store.class, QStore.class, PathInits.DIRECT2);

    public QLocation(String variable) {
        super(Location.class, forVariable(variable));
    }

    public QLocation(Path<? extends Location> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocation(PathMetadata metadata) {
        super(Location.class, metadata);
    }

}

