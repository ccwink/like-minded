/**
 * 用户类型
 */
export type UserType = {
    id: number;
    username: string;
    userAccount: string;
    avatarUrl: string;
    gender: number;
    profile: string;
    phone: string;
    email: string;
    userStatus: number;
    userRole: number;
    tags: string[];
    createTime: Date;
}