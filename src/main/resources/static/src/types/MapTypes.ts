export interface IStateProps {
    mapType: string,
    stateName: any;
    stations: any[];
    path: any;
    feature: object;
    fill: string;
    radius: number;
    i: number;
}

export interface IStateState {

}

export interface IMapProps {
    mapType?: string;
    maps: any;
    stations: any;
    hoverCallback?: Function;
    console?: boolean;
    outputMapHoverInfo?: Function;
}

export interface IMapState {
    maps: any;
    states: any;
    stations: any[];
    tooltipActive: boolean;
    translateX: number;
    translateY: number;
    translateOn: boolean;
    startPageX: number;
    startPageY: number;
    zoom: number;
    console: boolean;
    outputMapHoverInfo: Function | undefined;
}

export interface ICircleEvent {
    x: number,
    y: number,
    target: any
}