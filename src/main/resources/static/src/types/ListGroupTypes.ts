import { IStationDetail } from "./StationTypes";

export interface IListGroupInfoState {
    dropdownOpen: boolean
}

export interface IListGroupInfoProp {
    input: IStationDetail[],
    label: string,
    selectionCallback: Function
}