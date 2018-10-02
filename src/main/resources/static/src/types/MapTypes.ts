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
}

export interface IMapState {
    maps: any;
    states: any;
    stations: any[];
}