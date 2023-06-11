package whu.edu.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.cn.entity.Route;
import whu.edu.cn.mapper.LineMapper;
import whu.edu.cn.mapper.PointMapper;
import whu.edu.cn.mapper.TextEventMapper;
import whu.edu.cn.mapper.aftermatching.PointRouteAMMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TextEventController {
    @Autowired
    TextEventMapper textEventMapper;
    @Autowired
    PointMapper pointMapper;
    @Autowired
    PointRouteAMMapper pointRouteAMMapper;
    @Autowired
    LineMapper lineMapper;

    @PostMapping("/insertpointbytext")
    String insertPointByText(Integer disasterid, String routename, String poiname, Integer direction, double distance) {
        String returnstring = new String();
        Route route = textEventMapper.getNearestRouteofPOI(routename, poiname);
        List<Route> routeList = textEventMapper.getRouteByName(routename);
        double m = textEventMapper.getM(route.getGeom(), poiname);
        double length = textEventMapper.getLength(route.getGeom());
        if (direction == 1 || direction == 2 || direction == 8) {
            if (route.getY1() <= route.getY2()) {
                if (distance <= length * (1 - m)) {
                    double n = m + distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            } else {
                if (distance <= length * m) {
                    double n = m - distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 4 || direction == 5 || direction == 6) {
            if (route.getY1() >= route.getY2()) {
                if (distance <= length * (1 - m)) {
                    double n = m + distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                System.out.println("n = " + n);
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            } else {
                if (distance <= length * m) {
                    double n = m - distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 3) {
            if (route.getX1() <= route.getX2()) {
                if (distance <= length * (1 - m)) {
                    double n = m + distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double x2 = route.getX2();
                    double y2 = route.getY2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (x2 == routeList.get(i).getX1() && y2 == routeList.get(i).getY1()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                x2 = routeList.get(i).getX2();
                                y2 = routeList.get(i).getY2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            } else {
                if (distance <= length * m) {
                    double n = m - distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double x1 = route.getX1();
                    double y1 = route.getY1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (x1 == routeList.get(i).getX2() && y1 == routeList.get(i).getY2()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                x1 = routeList.get(i).getX1();
                                y1 = routeList.get(i).getY1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 7) {
            if (route.getX1() >= route.getX2()) {
                if (distance <= length * (1 - m)) {
                    double n = m + distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double x2 = route.getX2();
                    double y2 = route.getY2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (x2 == routeList.get(i).getX1() && y2 == routeList.get(i).getY1()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                x2 = routeList.get(i).getX2();
                                y2 = routeList.get(i).getY2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            } else {
                if (distance <= length * m) {
                    double n = m - distance / length;
                    String newpointgeom = textEventMapper.getPointGeom(route.getGeom(), n);
                    returnstring = newpointgeom;
                    pointMapper.insertPointEvent(n, newpointgeom, disasterid, route.getGid());
                    pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), route.getGeom(), disasterid, route.getGid());
                } else {
                    double x1 = route.getX1();
                    double y1 = route.getY1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (x1 == routeList.get(i).getX2() && y1 == routeList.get(i).getY2()) {
                            if (distance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (distance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                String newpointgeom = textEventMapper.getPointGeom(routeList.get(i).getGeom(), n);
                                returnstring = newpointgeom;
                                pointMapper.insertPointEvent(n, newpointgeom, disasterid, routeList.get(i).getGid());
                                pointRouteAMMapper.insertPointRouteAM(pointMapper.getlastpoint(), routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                break;
                            } else {
                                x1 = routeList.get(i).getX1();
                                y1 = routeList.get(i).getY1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                            }
                        }
                    }
                }
            }
        }
        return returnstring;
    }


    @PostMapping("/insertlinebytext")
    List<String> insertLineByText(Integer disasterid, String routename, String poiname, Integer direction, double startdistance, double enddistance) {
        List<String> routePass = new ArrayList<>();
        Route route = textEventMapper.getNearestRouteofPOI(routename, poiname);
        List<Route> routeList = textEventMapper.getRouteByName(routename);
        double m = textEventMapper.getM(route.getGeom(), poiname);
        double length = textEventMapper.getLength(route.getGeom());
        if (direction == 1 || direction == 2 || direction == 8) {
            if (route.getY1() <= route.getY2()) {
                int k = 0;
                int kk = 0;
                int w = 0;
                double fromm = 0.0;
                int ix = 0;
                if (startdistance <= length * (1 - m)) {
                    w = 1;
                    double n = m + startdistance / length;
                    fromm = n;
                } else {
                    w = 2;
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                fromm = n;
                                ix = i;
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * (1 - m)) {
                    if (w == 1) {
                        double n = m + enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, n);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(fromm, n, subLineGeom, disasterid, route.getGid());
                    }
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), fromm, n);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, n, subLineGeom, disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), fromm, 1);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, 1, subLineGeom, disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(0, n, subLineGeom2, disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, 1);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(fromm, 1, subLineGeom, disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(0, n, subLineGeom2, disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            } else {
                int k = 0;
                int kk = 0;
                int w = 0;
                double tom = 0.0;
                int ix = 0;
                if (startdistance <= length * m) {
                    w = 1;
                    double n = m - startdistance / length;
                    tom = n;
                } else {
                    w = 2;
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                ix = i;
                                double n = 1 - (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                tom = n;
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * m) {
                    if (w == 1) {
                        double n = m - enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), n, tom);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(n, tom, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(n, tom, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), 0, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(0, tom, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), 0, tom);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(0, tom, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 4 || direction == 5 || direction == 6) {
            if (route.getY1() >= route.getY2()) {
                int k = 0;
                int kk = 0;
                int w = 0;
                double fromm = 0.0;
                int ix = 0;
                if (startdistance <= length * (1 - m)) {
                    double n = m + startdistance / length;
                    w = 1;
                    fromm = n;
                } else {
                    w = 2;
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                fromm = n;
                                ix = i;
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * (1 - m)) {
                    if (w == 1) {
                        double n = m + enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, n);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(fromm, n, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), fromm, n);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());

                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), fromm, 1);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, 1, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, 1);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(fromm, 1, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            } else {
                int k = 0;
                int kk = 0;
                int w = 0;
                double tom = 0.0;
                int ix = 0;
                if (startdistance <= length * m) {
                    w = 1;
                    double n = m - startdistance / length;
                    tom = n;
                } else {
                    w = 2;
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                tom = n;
                                ix = i;
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * m) {
                    if (w == 1) {
                        double n = m - enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), n, tom);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(n, tom, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(n, tom, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), 0, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(0, tom, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), 0, tom);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(0, tom, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 3) {
            if (route.getX1() <= route.getX2()) {
                int k = 0;
                int kk = 0;
                int w = 0;
                double fromm = 0.0;
                int ix = 0;
                if (startdistance <= length * (1 - m)) {
                    w = 1;
                    double n = m + startdistance / length;
                    fromm = n;
                } else {
                    w = 2;
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                fromm = n;
                                ix = i;
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * (1 - m)) {
                    if (w == 1) {
                        double n = m + enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, n);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(fromm, n, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), fromm, n);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), fromm, 1);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, 1, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, 1);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(fromm, 1, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            } else {
                int k = 0;
                int kk = 0;
                int w = 0;
                double tom = 0.0;
                int ix = 0;
                if (startdistance <= length * m) {
                    w = 1;
                    double n = m - startdistance / length;
                    tom = n;
                } else {
                    w = 2;
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                ix = i;
                                double n = 1 - (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                tom = n;
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * m) {
                    if (w == 1) {
                        double n = m - enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), n, tom);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(0, tom, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(n, tom, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), 0, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(0, tom, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), 0, tom);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(0, tom, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            }
        } else if (direction == 7) {
            if (route.getX1() >= route.getX2()) {
                int k = 0;
                int kk = 0;
                int w = 0;
                double fromm = 0.0;
                int ix = 0;
                if (startdistance <= length * (1 - m)) {
                    double n = m + startdistance / length;
                    w = 1;
                    fromm = n;
                } else {
                    w = 2;
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                fromm = n;
                                ix = i;
                                break;
                            } else {
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * (1 - m)) {
                    if (w == 1) {
                        double n = m + enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, n);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(fromm, n, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y2 = route.getY2();
                    double x2 = route.getX2();
                    double lengthall = length * (1 - m);
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y2 == routeList.get(i).getY1() && x2 == routeList.get(i).getX1()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), fromm, n);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), fromm, 1);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(fromm, 1, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), fromm, 1);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(fromm, 1, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), 0, n);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(0, n, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y2 = routeList.get(i).getY2();
                                x2 = routeList.get(i).getX2();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            } else {
                int k = 0;
                int kk = 0;
                int w = 0;
                double tom = 0.0;
                int ix = 0;
                if (startdistance <= length * m) {
                    w = 1;
                    double n = m - startdistance / length;
                    tom = n;
                } else {
                    w = 2;
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (startdistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (startdistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                tom = n;
                                ix = i;
                                break;
                            } else {
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k + 1;
                                kk = kk + 1;
                            }
                        }
                    }
                }
                if (enddistance <= length * m) {
                    if (w == 1) {
                        double n = m - enddistance / length;
                        String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), n, tom);
                        routePass.add(subLineGeom);
                        lineMapper.insertLine(n, tom, route.getGeom(), disasterid, route.getGid());
                    }
                } else {
                    double y1 = route.getY1();
                    double x1 = route.getX1();
                    double lengthall = length * m;
                    for (int i = 0; i < routeList.size(); i++) {
                        if (y1 == routeList.get(i).getY2() && x1 == routeList.get(i).getX2()) {
                            if (enddistance <= textEventMapper.getLength(routeList.get(i).getGeom()) + lengthall) {
                                double n = 1 - (enddistance - lengthall) / textEventMapper.getLength(routeList.get(i).getGeom());
                                if (w == 2) {
                                    if (ix == i) {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(n, tom, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    } else {
                                        String subLineGeom = textEventMapper.getSubLineGeom(routeList.get(ix).getGeom(), 0, tom);
                                        routePass.add(subLineGeom);
                                        lineMapper.insertLine(0, tom, routeList.get(ix).getGeom(), disasterid, routeList.get(ix).getGid());
                                        String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                        routePass.add(subLineGeom2);
                                        lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    String subLineGeom = textEventMapper.getSubLineGeom(route.getGeom(), 0, tom);
                                    routePass.add(subLineGeom);
                                    lineMapper.insertLine(0, tom, route.getGeom(), disasterid, route.getGid());
                                    String subLineGeom2 = textEventMapper.getSubLineGeom(routeList.get(i).getGeom(), n, 1);
                                    routePass.add(subLineGeom2);
                                    lineMapper.insertLine(n, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                }
                                break;
                            } else {
                                if (kk > 0) {
                                    if (k < 0) {
                                        routePass.add(routeList.get(i).getGeom());
                                        lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                    }
                                } else {
                                    if (w == 1) {
                                        if (k <= 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    } else if (w == 2) {
                                        if (k < 0) {
                                            routePass.add(routeList.get(i).getGeom());
                                            lineMapper.insertLine(0, 1, routeList.get(i).getGeom(), disasterid, routeList.get(i).getGid());
                                        }
                                    }
                                }
                                y1 = routeList.get(i).getY1();
                                x1 = routeList.get(i).getX1();
                                lengthall = lengthall + textEventMapper.getLength(routeList.get(i).getGeom());
                                i = -1;
                                k = k - 1;
                            }
                        }
                    }
                }
            }
        }
        //textEventMapper.truncateTable();
        //for (int i = 0; i < routePass.size(); i++) {
        //    textEventMapper.insertTestLine(i + 1, routePass.get(i));
        //}
        return routePass;
    }
}
