import { IStationDetail } from "./StationTypes";

export interface ITooltipState {
    text: JSX.Element;
    x: number;
    y: number;
    station: IStationDetail | undefined,
    tooltipActive: boolean;
}

export interface ITooltipProps {
    text: Function;
    x: number;
    y: number;
    station: IStationDetail | undefined,
    tooltipActive: boolean;
}