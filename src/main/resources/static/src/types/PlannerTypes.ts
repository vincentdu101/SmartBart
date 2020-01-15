import { IStationInfo } from "../types/StationTypes";

export enum PlannerSectionType {
    ARRIVAL = <any>"ARRIVAL",
    DESTINATION = <any>"DESTINATION"
}

export interface IPlannerRequest {
    origin: string,
    destination: string,
    fare: number,
    origDateTime: string,
    destDateTime: string,
    clipper: number,
    tripTime: number,
    co2: number
}

export interface ISchedule {
    before: number, 
    after: number,
    request: IPlannerRequest[]
}

export interface IPlannerState {
    plans: any
}

export interface IPlannerProps {
    origin: string,
    destination: string
}

export interface IPlannerSectionState {
    stations: {value: string, label: string}[],
    origin: string,
    destination: string,
    maps: any,
    focusedStations: any,
    mapSelectedStations: IStationInfo[],
    mapStations: IStationInfo[],
    tooltipStation: IStationInfo | undefined,
    tooltipActive: boolean,
    tooltipTextCallback: Function
}

export interface IPlannerSectionProps {
    type: PlannerSectionType;
}