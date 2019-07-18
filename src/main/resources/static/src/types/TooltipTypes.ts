import { IStationInfo } from "./StationTypes";

export interface ITooltipState {
    text: JSX.Element;
    station: IStationInfo | undefined,
    tooltipActive: boolean;
    mapHeight: number;
    topBuffer: number;
    leftBuffer: number;
}

export interface ITooltipProps {
    text: Function;
    station: IStationInfo | undefined,
    tooltipActive: boolean;
    mapHeight: number;
    topBuffer?: number;
    leftBuffer?: number;
}