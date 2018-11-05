import { IStationInfo } from "./StationTypes";

export interface ITooltipState {
    text: JSX.Element;
    x: number;
    y: number;
    station: IStationInfo | undefined,
    tooltipActive: boolean;
}

export interface ITooltipProps {
    text: Function;
    x: number;
    y: number;
    station: IStationInfo | undefined,
    tooltipActive: boolean;
}