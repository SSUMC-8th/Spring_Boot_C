package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryImg is a Querydsl query type for InquiryImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryImg extends EntityPathBase<InquiryImg> {

    private static final long serialVersionUID = 1872774696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryImg inquiryImg = new QInquiryImg("inquiryImg");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInquiry inquiry;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public QInquiryImg(String variable) {
        this(InquiryImg.class, forVariable(variable), INITS);
    }

    public QInquiryImg(Path<? extends InquiryImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryImg(PathMetadata metadata, PathInits inits) {
        this(InquiryImg.class, metadata, inits);
    }

    public QInquiryImg(Class<? extends InquiryImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
    }

}

