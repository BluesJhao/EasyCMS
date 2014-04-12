package cn.easycms.service;

import cn.easycms.dao.SiteDaoImpl;
import cn.easycms.model.Site;
import cn.easycms.util.StringUtil;
import org.hibernate.Query;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hackingwu on 2014/4/4.
 */
public class SiteService {
    private SiteDaoImpl siteDaoImpl;

    public void setSiteDaoImpl(SiteDaoImpl siteDaoImpl) {
        this.siteDaoImpl = siteDaoImpl;
    }

    public Site findById(String siteid) {
        return (Site) siteDaoImpl.get(siteid);
    }

    public Site selectFirstByParId(String parId) {
        Query query = siteDaoImpl.getCurrentSession().createQuery("from Site where parId=? ");
        query.setParameter(0,parId);
        List list = query.list();

        return list==null||list.size()==0 ? null : (Site)list.get(0);
    }


    public List selectByParId(String parId) {
        return siteDaoImpl.getCurrentSession()
                .createQuery("from Site where parId=?")
                .setParameter(0,parId)
                .list();
    }

    public Boolean hasChildren(String id) {
        List list = selectByParId(id);
        return list==null||list.size()==0?false:true;

    }

    public Boolean haveSourcePath(String sourcePath) {
        List list = siteDaoImpl.getCurrentSession()
                .createQuery("from Site where sourcePath=?")
                .setParameter(0,sourcePath)
                .list();
        return list==null||list.size()==0?false:true;
    }

    public void insert(Site site) {
        siteDaoImpl.save(site);
    }

    public void update(Site site) {
        siteDaoImpl.update(site);
    }

    public void html(String id, ServletContext getServletContext, String contextPath, HttpServletRequest httpRequest, String loginName) {

        Site site = findById(id);
        //有site并且site有模板
        if (site!=null && StringUtil.isNotEmpty(site.getIndexTemplate())){
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("site",site);
            //contextPath 比getContextPath多了"/";
            data.put("contextPath",contextPath);
            data.put("contextPathNo",httpRequest.getContextPath());
            FreeMarkerUtil.createHTML();
        }
    }
}
