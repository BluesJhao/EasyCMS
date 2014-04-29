package cn.easycms.action;

import cn.easycms.base.BaseAction;
import cn.easycms.model.Channel;
import cn.easycms.model.Site;
import cn.easycms.service.ChannelService;

import java.util.List;

/**
 * Created by hackingwu on 2014/4/23.
 */
public class HtmlAction extends BaseAction{
    private ChannelService channelService;

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    private List<Channel> channelList;

    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    private Channel channel;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    private Site site;

    /**
     * 首页静态化页面
     * @return
     */
    public String indexConfirm(){
        return "indexConfirm";
    }
    /**
     * 栏目页静态化页面
     * @return
     */
    public String channel(){
        site=getManageSite();
        if (site!=null) {
            //获取当前管理站点
            if (channel!=null && channel.getId()!=null && channel.getId().trim().length()>0) {
                if (!channel.getId().equals("select")) {
                    channel=channelService.findById(channel.getId());
                }
                channelList=channelService.findBySiteAndParAndStateAndNavi(site, "",null,null);
                if (channelList!=null && channelList.size()>0) {
                    for (int i = 0; i < channelList.size(); i++) {
                        if (channelService.hasChildren(channelList.get(i).getId())) {
                            channelList.get(i).setHasChildren("1");
                        }
                        //权限操作
//                        if (!isAdminLogin() && !isSiteAdmin()) {
//                            //如果是普通管理员则设置是否有权限管理
//                            if (roleChannelService.haves(getLoginRoleIdsSql(), channelList.get(i).getId())) {
//                                channelList.get(i).setHaveChannelRole("1");
//                            }
//                        }
                    }
                }
                return "channelSelect";
            }else {
                //栏目管理页面
                //获取一级栏目
                channelList=channelService.findBySiteAndParAndStateAndNavi(site, "",null,null);
                //设置是否有子栏目
                if (channelList!=null && channelList.size()>0) {
                    for (int i = 0; i < channelList.size(); i++) {
                        if (channelService.hasChildren(channelList.get(i).getId())) {
                            channelList.get(i).setHasChildren("1");
                        }
                        //权限操作
//                        if (!isAdminLogin() && !isSiteAdmin()) {
//                            //如果是普通管理员则设置是否有权限管理
//                            if (roleChannelService.haves(getLoginRoleIdsSql(), channelList.get(i).getId())) {
//                                channelList.get(i).setHaveChannelRole("1");
//                            }
//                        }
                    }
                }
            }
        }
        return "channel";
    }
    /**
     * 信息静态化页面
     * @return
     */
    public String info(){
        site=getManageSite();
        if (site!=null) {
            //获取当前管理站点
            if (channel!=null && channel.getId()!=null && channel.getId().trim().length()>0) {
                if (!channel.getId().equals("select")) {
                    channel=channelService.findById(channel.getId());
                }
                channelList=channelService.findBySiteAndParAndStateAndNavi(site, "",null,null);
                if (channelList!=null && channelList.size()>0) {
                    for (int i = 0; i < channelList.size(); i++) {
                        if (channelService.hasChildren(channelList.get(i).getId())) {
                            channelList.get(i).setHasChildren("1");
                        }
                        //权限管理
//                        if (!isAdminLogin() && !isSiteAdmin()) {
//                            //如果是普通管理员则设置是否有权限管理
//                            if (roleChannelService.haves(getLoginRoleIdsSql(), channelList.get(i).getId())) {
//                                channelList.get(i).setHaveChannelRole("1");
//                            }
//                        }
                    }
                }
                return "channelSelect";
            }else {
                //栏目管理页面
                //获取一级栏目
                channelList=channelService.findBySiteAndParAndStateAndNavi(site, "",null,null);
                //设置是否有子栏目
                if (channelList!=null && channelList.size()>0) {
                    for (int i = 0; i < channelList.size(); i++) {
                        if (channelService.hasChildren(channelList.get(i).getId())) {
                            channelList.get(i).setHasChildren("1");
                        }
                        //权限管理
//                        if (!isAdminLogin() && !isSiteAdmin()) {
//                            //如果是普通管理员则设置是否有权限管理
//                            if (roleChannelService.haves(getLoginRoleIdsSql(), channelList.get(i).getId())) {
//                                channelList.get(i).setHaveChannelRole("1");
//                            }
//                        }
                    }
                }
            }
        }
        return "info";
    }
}