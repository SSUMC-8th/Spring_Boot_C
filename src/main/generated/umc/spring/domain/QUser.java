package umc.spring.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1348045193L;

    public static final QUser user = new QUser("user");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<umc.spring.domain.enums.Gender> gender = createEnum("gender", umc.spring.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final ListPath<Inquiry, QInquiry> inquiryList = this.<Inquiry, QInquiry>createList("inquiryList", Inquiry.class, QInquiry.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phone_number = createString("phone_number");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc.spring.domain.enums.SocialType> socialType = createEnum("socialType", umc.spring.domain.enums.SocialType.class);

    public final EnumPath<umc.spring.domain.enums.UserStatus> status = createEnum("status", umc.spring.domain.enums.UserStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<umc.spring.domain.mapping.UserAgree, umc.spring.domain.mapping.QUserAgree> userAgreeList = this.<umc.spring.domain.mapping.UserAgree, umc.spring.domain.mapping.QUserAgree>createList("userAgreeList", umc.spring.domain.mapping.UserAgree.class, umc.spring.domain.mapping.QUserAgree.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.domain.mapping.UserFood, umc.spring.domain.mapping.QUserFood> userFoodList = this.<umc.spring.domain.mapping.UserFood, umc.spring.domain.mapping.QUserFood>createList("userFoodList", umc.spring.domain.mapping.UserFood.class, umc.spring.domain.mapping.QUserFood.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission> userMissionList = this.<umc.spring.domain.mapping.UserMission, umc.spring.domain.mapping.QUserMission>createList("userMissionList", umc.spring.domain.mapping.UserMission.class, umc.spring.domain.mapping.QUserMission.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

