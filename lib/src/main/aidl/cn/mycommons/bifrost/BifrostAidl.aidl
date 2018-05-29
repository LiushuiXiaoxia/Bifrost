// BifrostAidl.aidl
package cn.mycommons.bifrost;

import cn.mycommons.bifrost.internal.Req;
import cn.mycommons.bifrost.internal.Resp;

// Declare any non-default types here with import statements

interface BifrostAidl {

    Resp exec(in Req req);
}