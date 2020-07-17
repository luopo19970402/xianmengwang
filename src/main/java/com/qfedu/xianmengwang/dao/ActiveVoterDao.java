package com.qfedu.xianmengwang.dao;

import com.qfedu.xianmengwang.dto.ActiveItemVoterDto;
import com.qfedu.xianmengwang.pojo.Active;
import com.qfedu.xianmengwang.pojo.ActiveItem;
import com.qfedu.xianmengwang.pojo.ActiveVoter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 22:29
 */
public interface ActiveVoterDao {
    @Insert("insert into t_activevoter(aid,aiid,uid,ip,ctime) values(#{aid},#{aiid},#{uid},1,now())")
    int insert(ActiveVoter ac);

    @Select("select a.dayticket-ifnull(t.ct,0) from t_active a right join ( select count(*) ct,aid from t_activevoter where date_format(ctime,'%Y-%m-%d')=date_format(now(),'%Y-%m-%d') and uid=#{uid} and aid=#{aid}) t on a.id=t.aid")
    Integer selectFreeTicket(@Param("uid") int uid, @Param("aid") int aid);
    @Select("select av.ip,av.ctime,u.nickname,u.phone from t_activevoter av left join t_user u on av.uid=u.id where av.aiid=#{aiid} order by av.ctime desc")
    List<ActiveItemVoterDto> selectByAiid(int aiid);

    @Select("select a.* from t_active a right join (select aid from t_activevoter where uid=#{uid} group by aid)av on a.id=av.aid")
    List<Active> selectByUid(int uid);

    @Select("select a.* from t_activeitem a right join (select aiid from t_activevoter where uid=#{uid} group by aiid)av on a.id=av.aiid")
    List<ActiveItem> selectByUidItem(int uid);
}
