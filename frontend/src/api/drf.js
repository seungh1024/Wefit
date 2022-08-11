const HOST = 'http://3.36.88.140:8080/api/v1/'
// const HOST = '3.34.252.133'

// const ACCOUNTS = 'accounts/'
// const COMMUNITY = 'community/'
// const COMMENTS = 'comments/'

export default {
  accounts: {
    login: () => HOST  + 'login',
    // logout: () => HOST + 'logout/',
    signup: () => HOST  + 'user',
    userInfo: () => HOST + 'user-info/',
    doubleCheck: () => HOST +'email/',
    socialSignup:() => HOST + 'social/googleSignup',
    socialLogin: () => HOST + 'social/googleLogin',
    logout: () =>HOST + 'logout',
    like: () => HOST + 'like/',
    // Token 으로 현재 user 판단
    // currentUserInfo: () => HOST + ACCOUNTS + 'user/',
    // // username으로 프로필 제공
    // profile: username => HOST + ACCOUNTS + 'profile/' + username,

    //signupdetail: () => HOST  + 'user-info/{user_id}',

    //findid: () => HOST + 'findid',
    
    //findpw : () =>  HOST + 'findpw',
  },
//   communities: {
//     // /community/
//     communities: () => HOST + COMMUNITY,
//     // /community/1/
//     community: reviewPk => HOST + COMMUNITY + `${reviewPk}/`,
//     likeReview: reviewPk => HOST + COMMUNITY + `${reviewPk}/` + 'like/',
//     comments: reviewPk => HOST + COMMUNITY + `${reviewPk}/` + COMMENTS,
//     comment: (reviewPk, commentPk) =>
//       HOST + COMMUNITY + `${reviewPk}/` + COMMENTS + `${commentPk}/`,
//   },
//   movies: {
//     movies: () => HOST + 'movies/'
//   }
}
