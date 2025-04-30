package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTerms is a Querydsl query type for MemberTerms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTerms extends EntityPathBase<MemberTerms> {

    private static final long serialVersionUID = -2034069055L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTerms memberTerms = new QMemberTerms("memberTerms");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.spring.domain.QMember member;

    public final BooleanPath status = createBoolean("status");

    public final umc.spring.domain.QTerms terms;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberTerms(String variable) {
        this(MemberTerms.class, forVariable(variable), INITS);
    }

    public QMemberTerms(Path<? extends MemberTerms> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTerms(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTerms(PathMetadata metadata, PathInits inits) {
        this(MemberTerms.class, metadata, inits);
    }

    public QMemberTerms(Class<? extends MemberTerms> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new umc.spring.domain.QMember(forProperty("member")) : null;
        this.terms = inits.isInitialized("terms") ? new umc.spring.domain.QTerms(forProperty("terms")) : null;
    }

}

